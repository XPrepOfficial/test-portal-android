package co.classplus.app.cms.ui.solutions;

import co.classplus.app.cms.data.model.solutions.TestSolutionResponse;
import co.classplus.app.cms.ui.base.MvpView;

public interface SolutionsView extends MvpView {

    void onTestSolutionsFetched(TestSolutionResponse.TestSolutionData testSolutionData);
}
