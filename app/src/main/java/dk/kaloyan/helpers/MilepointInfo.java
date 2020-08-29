package dk.kaloyan.helpers;

import android.os.Parcel;
import android.os.Parcelable;

public final class MilepointInfo implements Parcelable {
    private final String mMilepointId;
    private final String mTitle;
    private boolean mIsComplete = false;

    public MilepointInfo(String milepointId, String title) {
        this(milepointId, title, false);
    }

    public MilepointInfo(String milepointId, String title, boolean isComplete) {
        mMilepointId = milepointId;
        mTitle = title;
        mIsComplete = isComplete;
    }

    private MilepointInfo(Parcel source) {
        mMilepointId = source.readString();
        mTitle = source.readString();
        mIsComplete = source.readByte() == 1;
    }

    public String getMilepointId() {
        return mMilepointId;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean isComplete() {
        return mIsComplete;
    }

    public void setComplete(boolean complete) {
        mIsComplete = complete;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MilepointInfo that = (MilepointInfo) o;

        return mMilepointId.equals(that.mMilepointId);
    }

    @Override
    public int hashCode() {
        return mMilepointId.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mMilepointId);
        dest.writeString(mTitle);
        dest.writeByte((byte)(mIsComplete ? 1 : 0));
    }

    public static final Creator<MilepointInfo> CREATOR =
            new Creator<MilepointInfo>() {

                @Override
                public MilepointInfo createFromParcel(Parcel source) {
                    return new MilepointInfo(source);
                }

                @Override
                public MilepointInfo[] newArray(int size) {
                    return new MilepointInfo[size];
                }
            };

}
