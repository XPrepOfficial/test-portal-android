package co.classplus.cms.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionOption extends CmsNameId implements Parcelable {

    private Boolean isCorrect;
    private Boolean isSelected;
    private String solution;

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    protected QuestionOption(Parcel in) {
        super(in);
        byte isCorrectVal = in.readByte();
        isCorrect = isCorrectVal == 0x02 ? null : isCorrectVal != 0x00;
        byte isSelectedVal = in.readByte();
        isSelected = isSelectedVal == 0x02 ? null : isSelectedVal != 0x00;
        solution = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        if (isCorrect == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (isCorrect ? 0x01 : 0x00));
        }
        if (isSelected == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (isSelected ? 0x01 : 0x00));
        }
        dest.writeString(solution);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<QuestionOption> CREATOR = new Parcelable.Creator<QuestionOption>() {
        @Override
        public QuestionOption createFromParcel(Parcel in) {
            return new QuestionOption(in);
        }

        @Override
        public QuestionOption[] newArray(int size) {
            return new QuestionOption[size];
        }
    };
}