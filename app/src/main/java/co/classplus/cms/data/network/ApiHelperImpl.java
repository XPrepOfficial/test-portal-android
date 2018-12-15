package co.classplus.cms.data.network;

import javax.inject.Inject;
import javax.inject.Singleton;

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
}
