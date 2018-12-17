package co.classplus.cms.data.model.test;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import co.classplus.cms.data.model.base.SectionBaseModel;
import co.classplus.cms.data.model.question.SingleQuestion;

public class TestSection extends SectionBaseModel {

    @Expose
    @SerializedName("questions")
    private ArrayList<SingleQuestion> questions;

    protected TestSection(Parcel in) {
        super(in);
    }

    public ArrayList<SingleQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<SingleQuestion> questions) {
        this.questions = questions;
    }
}
