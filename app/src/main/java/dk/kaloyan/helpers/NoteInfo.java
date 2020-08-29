package dk.kaloyan.helpers;

import android.os.Parcel;
import android.os.Parcelable;

public final class NoteInfo implements Parcelable {
    private QuestInfo mQuest;
    private String mTitle;
    private String mText;

    public NoteInfo(QuestInfo quest, String title, String text) {
        mQuest = quest;
        mTitle = title;
        mText = text;
    }

    private NoteInfo(Parcel parcel) {
        mQuest = parcel.readParcelable(QuestInfo.class.getClassLoader());
        mTitle = parcel.readString();
        mText = parcel.readString();

    }

    public QuestInfo getQuest() {
        return mQuest;
    }

    public void setQuest(QuestInfo quest) {
        mQuest = quest;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    private String getCompareKey() {
        return mQuest.getQuestId() + "|" + mTitle + "|" + mText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteInfo that = (NoteInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mQuest, 0);
        parcel.writeString(mTitle);
        parcel.writeString(mText);
    }

    public static final Parcelable.Creator<NoteInfo> CREATOR = new Creator<NoteInfo>(){
        @Override
        public NoteInfo createFromParcel(Parcel parcel) {
            return new NoteInfo(parcel);
        }

        @Override
        public NoteInfo[] newArray(int i) {
            return new NoteInfo[0];
        }
    } ;
}
