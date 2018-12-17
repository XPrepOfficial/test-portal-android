package co.classplus.cms.data.model.question;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import co.classplus.cms.data.model.test.ParagraphModel;

public class Question implements Parcelable {

    @Expose
    @SerializedName("_id")
    private String _id;
    @Expose
    @SerializedName("paragraph")
    private ParagraphModel paragraph;
    @Expose
    @SerializedName("name")
    private String quesText;
    @Expose
    @SerializedName("hasMultipleAnswers")
    private boolean hasMultipleAnswer;
    private int positiveMarks;
    private int negativeMarks;
    @Expose
    @SerializedName("options")
    private ArrayList<QuestionOption> options;

    public Question() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public ParagraphModel getParagraph() {
        return paragraph;
    }

    public void setParagraph(ParagraphModel paragraph) {
        this.paragraph = paragraph;
    }

    public String getQuesText() {
        return quesText;
    }

    public void setQuesText(String quesText) {
        this.quesText = quesText;
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
        _id = in.readString();
        paragraph = (ParagraphModel) in.readValue(ParagraphModel.class.getClassLoader());
        quesText = in.readString();
        hasMultipleAnswer = in.readByte() != 0x00;
        positiveMarks = in.readInt();
        negativeMarks = in.readInt();
        if (in.readByte() == 0x01) {
            options = new ArrayList<>();
            in.readList(options, QuestionOption.class.getClassLoader());
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
        dest.writeString(_id);
        dest.writeValue(paragraph);
        dest.writeString(quesText);
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
