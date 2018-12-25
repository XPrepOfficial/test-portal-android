package co.classplus.app.cms.data.model.submit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import co.classplus.app.cms.data.model.base.BaseResponseModel;

public class SubmitTestResponse extends BaseResponseModel {

    @Expose
    @SerializedName("data")
    private SubmitTestData data;

    public SubmitTestData getData() {
        return data;
    }

    public void setData(SubmitTestData data) {
        this.data = data;
    }

    public class SubmitTestData {

        @Expose
        @SerializedName("markesScored")
        private int marksScored;
        @Expose
        @SerializedName("correctAnswers")
        private int correctAnswers;
        @Expose
        @SerializedName("numberOfQuestionsAttempted")
        private int numberOfQuestionsAttempted;
        @Expose
        @SerializedName("totalQuestion")
        private int totalQuestion;
        @Expose
        @SerializedName("timeTaken")
        private long timeTaken;

        public int getMarksScored() {
            return marksScored;
        }

        public void setMarksScored(int marksScored) {
            this.marksScored = marksScored;
        }

        public int getCorrectAnswers() {
            return correctAnswers;
        }

        public void setCorrectAnswers(int correctAnswers) {
            this.correctAnswers = correctAnswers;
        }

        public int getNumberOfQuestionsAttempted() {
            return numberOfQuestionsAttempted;
        }

        public void setNumberOfQuestionsAttempted(int numberOfQuestionsAttempted) {
            this.numberOfQuestionsAttempted = numberOfQuestionsAttempted;
        }

        public int getTotalQuestion() {
            return totalQuestion;
        }

        public void setTotalQuestion(int totalQuestion) {
            this.totalQuestion = totalQuestion;
        }

        public long getTimeTaken() {
            return timeTaken;
        }

        public void setTimeTaken(long timeTaken) {
            this.timeTaken = timeTaken;
        }
    }
}
