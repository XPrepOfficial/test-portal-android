package co.classplus.app.cms.data;

import android.content.Context;

import com.google.gson.JsonObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.classplus.app.cms.data.model.solutions.TestSolutionResponse;
import co.classplus.app.cms.data.model.submit.SubmitTestResponse;
import co.classplus.app.cms.data.model.test.TestGetResponse;
import co.classplus.app.cms.data.model.test.TestInstructionsResponse;
import co.classplus.app.cms.data.network.ApiHelper;
import co.classplus.app.cms.data.prefs.PreferencesHelper;
import co.classplus.app.cms.di.ApplicationContext;
import io.reactivex.Observable;

@Singleton
public class DataManagerImpl implements DataManager {

    private final Context mContext;
    private final ApiHelper mApiHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManagerImpl(@ApplicationContext Context context,
                           PreferencesHelper preferencesHelper,
                           ApiHelper apiHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public Observable<TestInstructionsResponse> getTestInstructions(String accessToken, String testId) {
        return mApiHelper.getTestInstructions(accessToken, testId);
    }

    @Override
    public Observable<TestGetResponse> getTestDetails(String accessToken, String testId) {
        return mApiHelper.getTestDetails(accessToken, testId);
    }

    @Override
    public Observable<TestGetResponse> startTestApi(String accessToken, JsonObject jsonObject) {
        return mApiHelper.startTestApi(accessToken, jsonObject);
    }

    @Override
    public Observable<SubmitTestResponse> submitTest(String accessToken, JsonObject jsonObject) {
        return mApiHelper.submitTest(accessToken, jsonObject);
    }

    @Override
    public Observable<TestSolutionResponse> getTestSolutions(String accessToken, String testId, String studentTestId) {
        return mApiHelper.getTestSolutions(accessToken, testId, studentTestId);
    }
}
