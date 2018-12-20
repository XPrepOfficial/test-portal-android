package co.classplus.cms.data;

import android.content.Context;

import com.google.gson.JsonObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.classplus.cms.data.model.solutions.TestSolutionResponse;
import co.classplus.cms.data.model.submit.SubmitTestResponse;
import co.classplus.cms.data.model.test.TestGetResponse;
import co.classplus.cms.data.model.test.TestInstructionsResponse;
import co.classplus.cms.data.network.ApiHelper;
import co.classplus.cms.data.prefs.PreferencesHelper;
import co.classplus.cms.di.ApplicationContext;
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
    public Observable<TestInstructionsResponse> getTestInstructions(String testId) {
        return mApiHelper.getTestInstructions(testId);
    }

    @Override
    public Observable<TestGetResponse> getTestDetails(String testId) {
        return mApiHelper.getTestDetails(testId);
    }

    @Override
    public Observable<TestGetResponse> startTestApi(JsonObject jsonObject) {
        return mApiHelper.startTestApi(jsonObject);
    }

    @Override
    public Observable<SubmitTestResponse> submitTest(JsonObject jsonObject) {
        return mApiHelper.submitTest(jsonObject);
    }

    @Override
    public Observable<TestSolutionResponse> getTestSolutions(String testId, String studentTestId) {
        return mApiHelper.getTestSolutions(testId, studentTestId);
    }
}
