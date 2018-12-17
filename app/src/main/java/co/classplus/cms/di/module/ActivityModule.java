package co.classplus.cms.di.module;

import android.app.Activity;
import android.content.Context;

import co.classplus.cms.di.ActivityContext;
import co.classplus.cms.di.PerActivity;
import co.classplus.cms.ui.base.BasePresenter;
import co.classplus.cms.ui.base.MvpPresenter;
import co.classplus.cms.ui.base.MvpView;
import co.classplus.cms.ui.instructions.InstructionsPresenter;
import co.classplus.cms.ui.instructions.InstructionsPresenterImpl;
import co.classplus.cms.ui.instructions.InstructionsView;
import co.classplus.cms.ui.question.SingleQuesPresenter;
import co.classplus.cms.ui.question.SingleQuesPresenterImpl;
import co.classplus.cms.ui.question.SingleQuesView;
import co.classplus.cms.ui.taketest.TestTakingPresenter;
import co.classplus.cms.ui.taketest.TestTakingPresenterImpl;
import co.classplus.cms.ui.taketest.TestTakingView;
import co.classplus.cms.utils.rx.SchedulerProvider;
import co.classplus.cms.utils.rx.SchedulerProviderImpl;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new SchedulerProviderImpl();
    }

    @Provides
    @PerActivity
    MvpPresenter<MvpView> provideMvpPresenter(
            BasePresenter<MvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SingleQuesPresenter<SingleQuesView> provideSingleQuesPresenter(
            SingleQuesPresenterImpl<SingleQuesView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    InstructionsPresenter<InstructionsView> provideInstructionsPresenter(
            InstructionsPresenterImpl<InstructionsView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    TestTakingPresenter<TestTakingView> provideTestTakingPresenter(
            TestTakingPresenterImpl<TestTakingView> presenter) {
        return presenter;
    }
}