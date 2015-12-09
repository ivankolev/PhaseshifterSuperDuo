package barqsoft.footballscores.widget;

import android.app.LauncherActivity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Binder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.R;
import barqsoft.footballscores.ScoresDBHelper;
import barqsoft.footballscores.scoresAdapter;
import model.Fixture;

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    private ArrayList<Fixture> itemList = new ArrayList<>();
    private Context context = null;
    private int appWidgetId;

    public WidgetDataProvider(Context context, Intent intent) {
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        final long identityToken = Binder.clearCallingIdentity();

        try (Cursor mCursor = context.getContentResolver().query(DatabaseContract.BASE_CONTENT_URI, null, null, null, null)) {
            assert mCursor != null;
            while (mCursor.moveToNext()) {
                Fixture fixture = new Fixture();
                fixture.setHomeTeam(mCursor.getString(scoresAdapter.COL_HOME));
                fixture.setAwayTeam(mCursor.getString(scoresAdapter.COL_AWAY));
                fixture.setScore(mCursor.getString(scoresAdapter.COL_HOME_GOALS) + " - " + mCursor.getString(scoresAdapter.COL_AWAY_GOALS));
                fixture.setMatchTime(mCursor.getString(scoresAdapter.COL_MATCHTIME));
                itemList.add(fixture);
            }
        }
        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    /*
    *Similar to getView of Adapter where instead of View
    *we return RemoteViews
    *
    */
    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.scores_list_item);
        Fixture fixture = itemList.get(position);
        remoteView.setTextViewText(R.id.home_name, fixture.getHomeTeam());
        remoteView.setTextViewText(R.id.away_name, fixture.getAwayTeam());
        remoteView.setTextViewText(R.id.score_textview, fixture.getScore());
        remoteView.setTextViewText(R.id.data_textview, fixture.getMatchTime());
        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }
}
