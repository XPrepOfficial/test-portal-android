package co.classplus.cms.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SingleQuestion extends Question implements Parcelable {

    private boolean isMarkedForReview;
    private Boolean isCorrect; //specifically made not primitive
    private Long duration;

    public boolean isMarkedForReview() {
        return isMarkedForReview;
    }

    public void setMarkedForReview(boolean markedForReview) {
        isMarkedForReview = markedForReview;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    protected SingleQuestion(Parcel in) {
        super(in);
        isMarkedForReview = in.readByte() != 0x00;
        byte isCorrectVal = in.readByte();
        isCorrect = isCorrectVal == 0x02 ? null : isCorrectVal != 0x00;
        duration = in.readByte() == 0x00 ? null : in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeByte((byte) (isMarkedForReview ? 0x01 : 0x00));
        if (isCorrect == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (isCorrect ? 0x01 : 0x00));
        }
        if (duration == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeLong(duration);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SingleQuestion> CREATOR = new Parcelable.Creator<SingleQuestion>() {
        @Override
        public SingleQuestion createFromParcel(Parcel in) {
            return new SingleQuestion(in);
        }

        @Override
        public SingleQuestion[] newArray(int size) {
            return new SingleQuestion[size];
        }
    };
}
