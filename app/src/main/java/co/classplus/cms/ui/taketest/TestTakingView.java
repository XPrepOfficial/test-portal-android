package co.classplus.cms.ui.taketest;

import co.classplus.cms.data.model.submit.SubmitTestResponse;
import co.classplus.cms.data.model.test.TestGetResponse;
import co.classplus.cms.ui.base.MvpView;

public interface TestTakingView extends MvpView {

    void onTestDetailsFetched(TestGetResponse testGetResponse);

    void onTestSubmitSuccess(SubmitTestResponse.SubmitTestData data);

    void onTestSubmitError();
}
