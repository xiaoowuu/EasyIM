package win.smartown.easyim.ui.target;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by smartown on 2018/2/7 15:43.
 * <br>
 * Desc:
 * <br>
 */
public class ConversationJumpTarget implements Parcelable {

    private String p2pActivityClass;
    private String teamActivityClass;

    public ConversationJumpTarget() {
    }

    protected ConversationJumpTarget(Parcel in) {
        p2pActivityClass = in.readString();
        teamActivityClass = in.readString();
    }

    public static final Creator<ConversationJumpTarget> CREATOR = new Creator<ConversationJumpTarget>() {
        @Override
        public ConversationJumpTarget createFromParcel(Parcel in) {
            return new ConversationJumpTarget(in);
        }

        @Override
        public ConversationJumpTarget[] newArray(int size) {
            return new ConversationJumpTarget[size];
        }
    };

    public String getP2pActivityClass() {
        return p2pActivityClass;
    }

    public void setP2pActivityClass(Class<? extends Activity> p2pActivityClass) {
        this.p2pActivityClass = p2pActivityClass.getName();
    }

    public String getTeamActivityClass() {
        return teamActivityClass;
    }

    public void setTeamActivityClass(Class<? extends Activity> teamActivityClass) {
        this.teamActivityClass = teamActivityClass.getName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(p2pActivityClass);
        parcel.writeString(teamActivityClass);
    }
}
