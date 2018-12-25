package co.classplus.app.cms.ui.solutions;

import android.os.Bundle;

import javax.inject.Inject;

import co.classplus.app.cms.data.DataManager;
import co.classplus.app.cms.data.network.retrofit.RetrofitException;
import co.classplus.app.cms.ui.base.BasePresenter;
import co.classplus.app.cms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class SolutionsPresenterImpl<V extends SolutionsView> extends BasePresenter<V> implements SolutionsPresenter<V> {

    private static final String API_TEST_SOLUTIONS = "API_TEST_SOLUTIONS";

    @Inject
    public SolutionsPresenterImpl(DataManager dataManager,
                                  SchedulerProvider schedulerProvider,
                                  CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void fetchTestSolutions(String accessToken, String testId, String studentTestId) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getTestSolutions(accessToken, testId, studentTestId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(testSolutionResponse -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                    getMvpView().onTestSolutionsFetched(testSolutionResponse.getData());
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                    Bundle bundle = new Bundle();
                    handleError((RetrofitException) throwable, bundle, API_TEST_SOLUTIONS);
                }));
    }
}
