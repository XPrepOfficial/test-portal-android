package co.classplus.app.cms.ui.solutions;

import co.classplus.app.cms.ui.base.MvpPresenter;

public interface SolutionsPresenter<V extends SolutionsView> extends MvpPresenter<V> {

    void fetchTestSolutions(String accessToken, String testId, String studentTestId);
}
