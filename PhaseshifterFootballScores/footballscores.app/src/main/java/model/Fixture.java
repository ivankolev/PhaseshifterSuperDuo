package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Fixture implements Parcelable {
    private String homeTeam;
    private String awayTeam;
    private String score;



    private String matchTime;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.homeTeam);
        dest.writeString(this.awayTeam);
        dest.writeString(this.score);
        dest.writeString(this.matchTime);
    }

    public Fixture() {
    }

    protected Fixture(Parcel in) {
        this.homeTeam = in.readString();
        this.awayTeam = in.readString();
        this.score = in.readString();
        this.matchTime = in.readString();
    }

    public static final Parcelable.Creator<Fixture> CREATOR = new Parcelable.Creator<Fixture>() {
        public Fixture createFromParcel(Parcel source) {
            return new Fixture(source);
        }

        public Fixture[] newArray(int size) {
            return new Fixture[size];
        }
    };

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }
}
