package dk.kaloyan.helpers;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public final class QuestInfo implements Parcelable {
    private final String mQuestId;
    private final String mTitle;
    private final List<MilepointInfo> mMilepoints;

    public QuestInfo(String questId, String title, List<MilepointInfo> milepoints) {
        mQuestId = questId;
        mTitle = title;
        mMilepoints = milepoints;
    }

    private QuestInfo(Parcel source) {
        mQuestId = source.readString();
        mTitle = source.readString();
        mMilepoints = new ArrayList<>();
        source.readTypedList(mMilepoints, MilepointInfo.CREATOR);
    }

    public String getQuestId() {
        return mQuestId;
    }

    public String getTitle() {
        return mTitle;
    }

    public List<MilepointInfo> getMilepoints() {
        return mMilepoints;
    }

    public boolean[] getMilepointsCompletionStatus() {
        boolean[] status = new boolean[mMilepoints.size()];

        for(int i = 0; i < mMilepoints.size(); i++)
            status[i] = mMilepoints.get(i).isComplete();

        return status;
    }

    public void setMilepointsCompletionStatus(boolean[] status) {
        for(int i = 0; i < mMilepoints.size(); i++)
            mMilepoints.get(i).setComplete(status[i]);
    }

    public MilepointInfo getMilepoint(String MilepointId) {
        for(MilepointInfo milepointInfo : mMilepoints) {
            if(MilepointId.equals(milepointInfo.getMilepointId()))
                return milepointInfo;
        }
        return null;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestInfo that = (QuestInfo) o;

        return mQuestId.equals(that.mQuestId);

    }

    @Override
    public int hashCode() {
        return mQuestId.hashCode();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQuestId);
        dest.writeString(mTitle);
        dest.writeTypedList(mMilepoints);
    }

    public static final Creator<QuestInfo> CREATOR =
            new Creator<QuestInfo>() {

                @Override
                public QuestInfo createFromParcel(Parcel source) {
                    return new QuestInfo(source);
                }

                @Override
                public QuestInfo[] newArray(int size) {
                    return new QuestInfo[size];
                }
            };

}
