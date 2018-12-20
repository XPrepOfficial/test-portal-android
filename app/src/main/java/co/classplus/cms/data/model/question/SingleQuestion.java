package co.classplus.cms.data.model.question;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleQuestion extends Question implements Parcelable {

    private boolean isMarkedForReview;
    @Expose
    @SerializedName("isCorrect")
    private Boolean isCorrect; //specifically made not primitive
    @Expose
    @SerializedName("timeTaken")
    private long duration;
    @Expose
    @SerializedName("isAttempted")
    private boolean isAttempted;

    public SingleQuestion() {
        super();
    }

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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isAttempted() {
        return isAttempted;
    }

    public void setAttempted(boolean attempted) {
        isAttempted = attempted;
    }

    protected SingleQuestion(Parcel in) {
        super(in);
        isMarkedForReview = in.readByte() != 0x00;
        byte isCorrectVal = in.readByte();
        isCorrect = isCorrectVal == 0x02 ? null : isCorrectVal != 0x00;
        duration = in.readLong();
        isAttempted = in.readByte() != 0x00;
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
        dest.writeLong(duration);
        dest.writeByte((byte) (isAttempted ? 0x01 : 0x00));
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
