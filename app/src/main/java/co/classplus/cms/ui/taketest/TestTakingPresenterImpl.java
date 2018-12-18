package co.classplus.cms.ui.taketest;

import android.os.Bundle;

import javax.inject.Inject;

import co.classplus.cms.data.DataManager;
import co.classplus.cms.data.network.retrofit.RetrofitException;
import co.classplus.cms.ui.base.BasePresenter;
import co.classplus.cms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class TestTakingPresenterImpl<V extends TestTakingView> extends BasePresenter<V> implements TestTakingPresenter<V> {

    private static final String PARAM_TEST_ID = "PARAM_TEST_ID";
    private static final String API_TEST_DETAILS = "API_TEST_DETAILS";

    @Inject
    public TestTakingPresenterImpl(DataManager dataManager,
                                   SchedulerProvider schedulerProvider,
                                   CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void fetchTestDetails(String testId) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getTestDetails(testId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(testGetResponse -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                    getMvpView().onTestDetailsFetched(testGetResponse);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                    Bundle bundle = new Bundle();
                    bundle.putString(PARAM_TEST_ID, testId);
                    handleError((RetrofitException) throwable, bundle, API_TEST_DETAILS);
                }));
    }
}
