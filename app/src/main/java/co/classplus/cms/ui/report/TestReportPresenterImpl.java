package co.classplus.cms.ui.report;

import javax.inject.Inject;

import co.classplus.cms.data.DataManager;
import co.classplus.cms.ui.base.BasePresenter;
import co.classplus.cms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class TestReportPresenterImpl<V extends TestReportView> extends BasePresenter<V> implements TestReportPresenter<V> {

    @Inject
    public TestReportPresenterImpl(DataManager dataManager,
                                   SchedulerProvider schedulerProvider,
                                   CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
