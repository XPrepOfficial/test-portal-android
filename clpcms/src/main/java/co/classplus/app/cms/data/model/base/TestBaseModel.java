package co.classplus.app.cms.data.model.base;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestBaseModel extends CmsNameId {

    @Expose
    @SerializedName("instructions")
    private String instructions;
    @Expose
    @SerializedName("totalNumberOfQuestions")
    private int totalNumberOfQuestions;
    @Expose
    @SerializedName("totalMarks")
    private int totalMarks;
    @Expose
    @SerializedName("duration")
    private long duration;
    @Expose
    @SerializedName("sytemInstructions")
    private String systemInstructions;

    protected TestBaseModel(Parcel in) {
        super(in);
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getTotalNumberOfQuestions() {
        return totalNumberOfQuestions;
    }

    public void setTotalNumberOfQuestions(int totalNumberOfQuestions) {
        this.totalNumberOfQuestions = totalNumberOfQuestions;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getSystemInstructions() {
        return systemInstructions;
    }

    public void setSystemInstructions(String systemInstructions) {
        this.systemInstructions = systemInstructions;
    }
}
