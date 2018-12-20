package co.classplus.cms.data.network;

import com.google.gson.JsonObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.classplus.cms.data.model.solutions.TestSolutionResponse;
import co.classplus.cms.data.model.submit.SubmitTestResponse;
import co.classplus.cms.data.model.test.TestGetResponse;
import co.classplus.cms.data.model.test.TestInstructionsResponse;
import io.reactivex.Observable;

@Singleton
public class ApiHelperImpl implements ApiHelper {

    @Inject
    public ApiHelperImpl() {
    }

    @Override
    public Observable<TestInstructionsResponse> getTestInstructions(String testId) {
        return NetworkSingleton.getInstance()
                .getNetworkClient()
                .create(ApiHelper.class).getTestInstructions(testId);
    }

    @Override
    public Observable<TestGetResponse> getTestDetails(String testId) {
        return NetworkSingleton.getInstance()
                .getNetworkClient()
                .create(ApiHelper.class).getTestDetails(testId);
    }

    @Override
    public Observable<TestGetResponse> startTestApi(JsonObject jsonObject) {
        return NetworkSingleton.getInstance()
                .getNetworkClient()
                .create(ApiHelper.class).startTestApi(jsonObject);
    }

    @Override
    public Observable<SubmitTestResponse> submitTest(JsonObject jsonObject) {
        return NetworkSingleton.getInstance()
                .getNetworkClient()
                .create(ApiHelper.class).submitTest(jsonObject);
    }

    @Override
    public Observable<TestSolutionResponse> getTestSolutions(String testId, String studentTestId) {
        return NetworkSingleton.getInstance()
                .getNetworkClient()
                .create(ApiHelper.class).getTestSolutions(testId, studentTestId);
    }
}
