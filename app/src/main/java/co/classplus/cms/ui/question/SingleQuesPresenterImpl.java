package co.classplus.cms.ui.question;

import javax.inject.Inject;

import co.classplus.cms.data.DataManager;
import co.classplus.cms.ui.base.BasePresenter;
import co.classplus.cms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class SingleQuesPresenterImpl<V extends SingleQuesView> extends BasePresenter<V> implements SingleQuesPresenter<V> {

    @Inject
    public SingleQuesPresenterImpl(DataManager dataManager,
                                   SchedulerProvider schedulerProvider,
                                   CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
