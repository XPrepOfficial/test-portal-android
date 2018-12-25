package co.classplus.app.cms.data.model.test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import co.classplus.app.cms.data.model.base.BaseResponseModel;

public class TestGetResponse extends BaseResponseModel {

    @Expose
    @SerializedName("data")
    private TestGetData data;

    public TestGetData getData() {
        return data;
    }

    public void setData(TestGetData data) {
        this.data = data;
    }

    public class TestGetData {

        @Expose
        @SerializedName("studentTestId")
        private String studentTestId;
        @Expose
        @SerializedName("test")
        private SingleTest test;

        public String getStudentTestId() {
            return studentTestId;
        }

        public void setStudentTestId(String studentTestId) {
            this.studentTestId = studentTestId;
        }

        public SingleTest getTest() {
            return test;
        }

        public void setTest(SingleTest test) {
            this.test = test;
        }
    }
}
