package eugene.com.alias;

import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable {
    private String name;
    private String player1;
    private String player2;
    private int score;

    private Team(Parcel in) {
        name = in.readString();
        player1 = in.readString();
        player2 = in.readString();
        score = in.readInt();
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public Team(String name, String player1, String player2) {
        this.name = name;
        this.player1 = player1;
        this.player2 = player2;
        this.score = 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(player1);
        dest.writeString(player2);
        dest.writeInt(score);
    }
}
