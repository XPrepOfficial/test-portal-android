package co.classplus.app.cms.ui.report;

import javax.inject.Inject;

import co.classplus.app.cms.data.DataManager;
import co.classplus.app.cms.ui.base.BasePresenter;
import co.classplus.app.cms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class TestReportPresenterImpl<V extends TestReportView> extends BasePresenter<V> implements TestReportPresenter<V> {

    @Inject
    public TestReportPresenterImpl(DataManager dataManager,
                                   SchedulerProvider schedulerProvider,
                                   CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
