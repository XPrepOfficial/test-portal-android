package co.classplus.cms.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Question implements Parcelable {

    private CmsNameId paragraph;
    private boolean hasMultipleAnswer;
    private int positiveMarks;
    private int negativeMarks;
    private ArrayList<QuestionOption> options;

    public CmsNameId getParagraph() {
        return paragraph;
    }

    public void setParagraph(CmsNameId paragraph) {
        this.paragraph = paragraph;
    }

    public boolean isHasMultipleAnswer() {
        return hasMultipleAnswer;
    }

    public void setHasMultipleAnswer(boolean hasMultipleAnswer) {
        this.hasMultipleAnswer = hasMultipleAnswer;
    }

    public int getPositiveMarks() {
        return positiveMarks;
    }

    public void setPositiveMarks(int positiveMarks) {
        this.positiveMarks = positiveMarks;
    }

    public int getNegativeMarks() {
        return negativeMarks;
    }

    public void setNegativeMarks(int negativeMarks) {
        this.negativeMarks = negativeMarks;
    }

    public ArrayList<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<QuestionOption> options) {
        this.options = options;
    }

    protected Question(Parcel in) {
        paragraph = (CmsNameId) in.readValue(CmsNameId.class.getClassLoader());
        hasMultipleAnswer = in.readByte() != 0x00;
        positiveMarks = in.readInt();
        negativeMarks = in.readInt();
        if (in.readByte() == 0x01) {
            options = new ArrayList<>();
            in.readList(options, CmsNameId.class.getClassLoader());
        } else {
            options = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(paragraph);
        dest.writeByte((byte) (hasMultipleAnswer ? 0x01 : 0x00));
        dest.writeInt(positiveMarks);
        dest.writeInt(negativeMarks);
        if (options == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(options);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
