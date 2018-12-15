package co.classplus.cms.ui.instructions;

import android.os.Bundle;

import javax.inject.Inject;

import co.classplus.cms.data.DataManager;
import co.classplus.cms.data.network.retrofit.RetrofitException;
import co.classplus.cms.ui.base.BasePresenter;
import co.classplus.cms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class InstructionsPresenterImpl<V extends InstructionsView> extends BasePresenter<V> implements InstructionsPresenter<V> {

    private static final String API_TEST_INSTRUCTIONS = "API_TEST_INSTRUCTIONS";
    private static final String PARAM_TEST_ID = "PARAM_TEST_ID";

    @Inject
    public InstructionsPresenterImpl(DataManager dataManager,
                                     SchedulerProvider schedulerProvider,
                                     CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void fetchTestInstructions(String testId) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getTestInstructions(testId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(testInstructionsResponse -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                    getMvpView().onTestInstructionsFetched(testInstructionsResponse.getTestInstructionsData().getTestInstructions());
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                    Bundle bundle = new Bundle();
                    bundle.putString(PARAM_TEST_ID, testId);
                    handleError((RetrofitException) throwable, bundle, API_TEST_INSTRUCTIONS);
                }));
    }
}
