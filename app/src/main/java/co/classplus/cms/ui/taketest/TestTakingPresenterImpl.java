package co.classplus.cms.ui.taketest;

import javax.inject.Inject;

import co.classplus.cms.data.DataManager;
import co.classplus.cms.ui.base.BasePresenter;
import co.classplus.cms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class TestTakingPresenterImpl<V extends TestTakingView> extends BasePresenter<V> implements TestTakingPresenter<V> {

    @Inject
    public TestTakingPresenterImpl(DataManager dataManager,
                                   SchedulerProvider schedulerProvider,
                                   CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
