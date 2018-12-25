package co.classplus.app.cms.data.model.test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import co.classplus.app.cms.data.model.base.BaseResponseModel;

public class TestInstructionsResponse extends BaseResponseModel {

    @Expose
    @SerializedName("data")
    private TestInstructionsData testInstructionsData;

    public TestInstructionsData getTestInstructionsData() {
        return testInstructionsData;
    }

    public void setTestInstructionsData(TestInstructionsData testInstructionsData) {
        this.testInstructionsData = testInstructionsData;
    }

    public class TestInstructionsData {

        @Expose
        @SerializedName("test")
        private TestInstructions testInstructions;

        public TestInstructions getTestInstructions() {
            return testInstructions;
        }

        public void setTestInstructions(TestInstructions testInstructions) {
            this.testInstructions = testInstructions;
        }
    }
}
