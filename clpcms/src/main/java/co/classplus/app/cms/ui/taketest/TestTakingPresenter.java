package co.classplus.app.cms.ui.taketest;

import co.classplus.app.cms.data.model.test.SingleTest;
import co.classplus.app.cms.ui.base.MvpPresenter;

public interface TestTakingPresenter<V extends TestTakingView> extends MvpPresenter<V> {

    void fetchTestDetails(String accessToken, String testId, int studentId);

    void submitTest(String accessToken, SingleTest singleTest, String studentTestId, long timeTaken);
}
