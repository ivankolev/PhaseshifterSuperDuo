package it.jaschke.alexandria.services;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.ResponseBody;

import it.jaschke.alexandria.MainActivity;
import it.jaschke.alexandria.R;
import it.jaschke.alexandria.data.AlexandriaContract;
import it.jaschke.alexandria.data.bookSchema.BookInfo;
import it.jaschke.alexandria.data.bookSchema.BookResult;
import it.jaschke.alexandria.data.bookSchema.ImageLinks;
import it.jaschke.alexandria.data.bookSchema.VolumeInfo;
import retrofit.Call;
import retrofit.Callback;
import retrofit.JacksonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 */
public class BookService extends IntentService {

    private final String LOG_TAG = BookService.class.getSimpleName();

    public static final String FETCH_BOOK = "it.jaschke.alexandria.services.action.FETCH_BOOK";
    public static final String DELETE_BOOK = "it.jaschke.alexandria.services.action.DELETE_BOOK";

    public static final String EAN = "it.jaschke.alexandria.services.extra.EAN";

    public static final String BASE_URL = "https://www.googleapis.com";

    public BookService() {
        super("Alexandria");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (FETCH_BOOK.equals(action)) {
                final String ean = intent.getStringExtra(EAN);
                fetchBook(ean);
            } else if (DELETE_BOOK.equals(action)) {
                final String ean = intent.getStringExtra(EAN);
                deleteBook(ean);
            }
        }
    }

    /**
     * Handle action deleteBook in the provided background thread with the provided
     * parameters.
     */
    private void deleteBook(String ean) {
        if(ean!=null) {
            getContentResolver().delete(AlexandriaContract.BookEntry.buildBookUri(Long.parseLong(ean)), null, null);
        }
    }

    /**
     * Handle action fetchBook in the provided background thread with the provided
     * parameters.
     */
    private void fetchBook(String ean) {

        if(ean.length()!=13){
            return;
        }

        Cursor bookEntry = getContentResolver().query(
                AlexandriaContract.BookEntry.buildBookUri(Long.parseLong(ean)),
                null, // leaving "columns" null just returns all the columns.
                null, // cols for "where" clause
                null, // values for "where" clause
                null  // sort order
        );

        assert bookEntry != null;
        if(bookEntry.getCount()>0){
            bookEntry.close();
            return;
        }

        bookEntry.close();
        fetchBookFromGoogle(ean);
    }

    private void writeBackInfo(BookInfo result, String ean) {
        try {
            VolumeInfo volumeInfo;
            if(result.hasVolumeInfo()){
                volumeInfo = result.getVolumeInfo();
            }else{
                Intent messageIntent = new Intent(MainActivity.MESSAGE_EVENT);
                messageIntent.putExtra(MainActivity.MESSAGE_KEY,getResources().getString(R.string.not_found));
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(messageIntent);
                return;
            }

            String title = volumeInfo.getTitle();

            String subtitle = "";
            if(volumeInfo.hasSubtitle()) {
                subtitle = volumeInfo.getSubtitle();
            }

            String desc="";
            if(volumeInfo.hasDescription()){
                desc = volumeInfo.getDescription();
            }

            String imgUrl = "";
            if(volumeInfo.hasImageLinks()) {
                ImageLinks imageLinks = volumeInfo.getImageLinks();
                if(imageLinks.hasThumbnail()){
                    imgUrl = imageLinks.getThumbnail();
                }
            }

            writeBackBook(ean, title, subtitle, desc, imgUrl);

            if(volumeInfo.hasAuthors()) {
                writeBackAuthors(ean, new JSONArray(volumeInfo.getAuthors()));
            }
            if(volumeInfo.hasCategories()){
                writeBackCategories(ean, new JSONArray(volumeInfo.getCategories()));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error ", e);
        }
    }

    private void fetchBookFromGoogle(final String ean) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        GoogleBook googleBook = retrofit.create(GoogleBook.class);
        Call<BookResult> call = googleBook.queryBy(ean);
        call.enqueue(new Callback<BookResult>() {
            @Override
            public void onResponse(Response<BookResult> response, Retrofit retrofit) {
                // response.isSuccess() is true if the response code is 2xx
                if (response.isSuccess()) {
                    BookResult bookResult = response.body();
                    BookInfo bookInfo = bookResult.getItems().get(0);
                    writeBackInfo(bookInfo, ean);
                } else {
                    int statusCode = response.code();

                    // handle request errors yourself
                    ResponseBody errorBody = response.errorBody();
                    Log.d(LOG_TAG,"Protocol error: " + errorBody.toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(LOG_TAG,"Network Failure");
                Toast.makeText(getApplicationContext(),
                        R.string.network_error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void writeBackBook(String ean, String title, String subtitle, String desc, String imgUrl) {
        ContentValues values= new ContentValues();
        values.put(AlexandriaContract.BookEntry._ID, ean);
        values.put(AlexandriaContract.BookEntry.TITLE, title);
        values.put(AlexandriaContract.BookEntry.IMAGE_URL, imgUrl);
        values.put(AlexandriaContract.BookEntry.SUBTITLE, subtitle);
        values.put(AlexandriaContract.BookEntry.DESC, desc);
        getContentResolver().insert(AlexandriaContract.BookEntry.CONTENT_URI,values);
    }

    private void writeBackAuthors(String ean, JSONArray jsonArray) throws JSONException {
        ContentValues values= new ContentValues();
        for (int i = 0; i < jsonArray.length(); i++) {
            values.put(AlexandriaContract.AuthorEntry._ID, ean);
            values.put(AlexandriaContract.AuthorEntry.AUTHOR, jsonArray.getString(i));
            getContentResolver().insert(AlexandriaContract.AuthorEntry.CONTENT_URI, values);
            values= new ContentValues();
        }
    }

    private void writeBackCategories(String ean, JSONArray jsonArray) throws JSONException {
        ContentValues values= new ContentValues();
        for (int i = 0; i < jsonArray.length(); i++) {
            values.put(AlexandriaContract.CategoryEntry._ID, ean);
            values.put(AlexandriaContract.CategoryEntry.CATEGORY, jsonArray.getString(i));
            getContentResolver().insert(AlexandriaContract.CategoryEntry.CONTENT_URI, values);
            values= new ContentValues();
        }
    }

    public interface GoogleBook {
        @GET("/books/v1/volumes")
        Call<BookResult> queryBy(
                @Query("q") String ean
        );
    }
 }