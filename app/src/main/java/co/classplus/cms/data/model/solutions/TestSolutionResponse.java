package co.classplus.cms.data.model.solutions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import co.classplus.cms.data.model.base.BaseResponseModel;
import co.classplus.cms.data.model.question.SingleQuestion;

public class TestSolutionResponse extends BaseResponseModel {

    @Expose
    @SerializedName("data")
    private TestSolutionData data;

    public TestSolutionData getData() {
        return data;
    }

    public void setData(TestSolutionData data) {
        this.data = data;
    }

    public class TestSolutionData {

        @Expose
        @SerializedName("questions")
        private ArrayList<SingleQuestion> questions;

        public ArrayList<SingleQuestion> getQuestions() {
            return questions;
        }

        public void setQuestions(ArrayList<SingleQuestion> questions) {
            this.questions = questions;
        }
    }
}
