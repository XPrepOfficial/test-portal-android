package co.classplus.cms.ui.solutions;

import co.classplus.cms.ui.base.MvpPresenter;

public interface SolutionsPresenter<V extends SolutionsView> extends MvpPresenter<V> {
    void fetchTestSolutions(String testId, String studentTestId);
}
