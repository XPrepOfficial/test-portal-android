package co.classplus.cms.ui.taketest;

import co.classplus.cms.data.model.test.SingleTest;
import co.classplus.cms.ui.base.MvpPresenter;

public interface TestTakingPresenter<V extends TestTakingView> extends MvpPresenter<V> {

    void fetchTestDetails(String accessToken, String testId, int studentId);

    void submitTest(String accessToken, SingleTest singleTest, String studentTestId, long timeTaken);
}
