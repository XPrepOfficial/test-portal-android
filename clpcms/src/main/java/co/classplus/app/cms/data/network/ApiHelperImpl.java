package co.classplus.app.cms.data.network;

import com.google.gson.JsonObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.classplus.app.cms.data.model.solutions.TestSolutionResponse;
import co.classplus.app.cms.data.model.submit.SubmitTestResponse;
import co.classplus.app.cms.data.model.test.TestGetResponse;
import co.classplus.app.cms.data.model.test.TestInstructionsResponse;
import io.reactivex.Observable;

@Singleton
public class ApiHelperImpl implements ApiHelper {

    @Inject
    public ApiHelperImpl() {
    }

    @Override
    public Observable<TestInstructionsResponse> getTestInstructions(String accessToken, String testId) {
        return NetworkSingleton.getInstance()
                .getNetworkClient()
                .create(ApiHelper.class).getTestInstructions(accessToken, testId);
    }

    @Override
    public Observable<TestGetResponse> getTestDetails(String accessToken, String testId) {
        return NetworkSingleton.getInstance()
                .getNetworkClient()
                .create(ApiHelper.class).getTestDetails(accessToken, testId);
    }

    @Override
    public Observable<TestGetResponse> startTestApi(String accessToken, JsonObject jsonObject) {
        return NetworkSingleton.getInstance()
                .getNetworkClient()
                .create(ApiHelper.class).startTestApi(accessToken, jsonObject);
    }

    @Override
    public Observable<SubmitTestResponse> submitTest(String accessToken, JsonObject jsonObject) {
        return NetworkSingleton.getInstance()
                .getNetworkClient()
                .create(ApiHelper.class).submitTest(accessToken, jsonObject);
    }

    @Override
    public Observable<TestSolutionResponse> getTestSolutions(String accessToken, String testId, String studentTestId) {
        return NetworkSingleton.getInstance()
                .getNetworkClient()
                .create(ApiHelper.class).getTestSolutions(accessToken, testId, studentTestId);
    }
}
