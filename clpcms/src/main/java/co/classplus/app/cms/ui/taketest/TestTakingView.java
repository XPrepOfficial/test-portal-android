package co.classplus.app.cms.ui.taketest;

import co.classplus.app.cms.data.model.submit.SubmitTestResponse;
import co.classplus.app.cms.data.model.test.TestGetResponse;
import co.classplus.app.cms.ui.base.MvpView;

public interface TestTakingView extends MvpView {

    void onTestDetailsFetched(TestGetResponse testGetResponse);

    void onTestSubmitSuccess(SubmitTestResponse.SubmitTestData data);

    void onTestSubmitError();
}
