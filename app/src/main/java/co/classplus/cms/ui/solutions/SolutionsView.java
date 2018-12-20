package co.classplus.cms.ui.solutions;

import co.classplus.cms.data.model.solutions.TestSolutionResponse;
import co.classplus.cms.ui.base.MvpView;

public interface SolutionsView extends MvpView {

    void onTestSolutionsFetched(TestSolutionResponse.TestSolutionData testSolutionData);
}
