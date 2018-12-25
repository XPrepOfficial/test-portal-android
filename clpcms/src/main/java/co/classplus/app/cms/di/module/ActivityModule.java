package co.classplus.app.cms.di.module;

import android.app.Activity;
import android.content.Context;

import co.classplus.app.cms.di.ActivityContext;
import co.classplus.app.cms.di.PerActivity;
import co.classplus.app.cms.ui.base.BasePresenter;
import co.classplus.app.cms.ui.base.MvpPresenter;
import co.classplus.app.cms.ui.base.MvpView;
import co.classplus.app.cms.ui.instructions.InstructionsPresenter;
import co.classplus.app.cms.ui.instructions.InstructionsPresenterImpl;
import co.classplus.app.cms.ui.instructions.InstructionsView;
import co.classplus.app.cms.ui.question.SingleQuesPresenter;
import co.classplus.app.cms.ui.question.SingleQuesPresenterImpl;
import co.classplus.app.cms.ui.question.SingleQuesView;
import co.classplus.app.cms.ui.report.TestReportPresenter;
import co.classplus.app.cms.ui.report.TestReportPresenterImpl;
import co.classplus.app.cms.ui.report.TestReportView;
import co.classplus.app.cms.ui.solutions.SolutionsPresenter;
import co.classplus.app.cms.ui.solutions.SolutionsPresenterImpl;
import co.classplus.app.cms.ui.solutions.SolutionsView;
import co.classplus.app.cms.ui.taketest.TestTakingPresenter;
import co.classplus.app.cms.ui.taketest.TestTakingPresenterImpl;
import co.classplus.app.cms.ui.taketest.TestTakingView;
import co.classplus.app.cms.utils.rx.SchedulerProvider;
import co.classplus.app.cms.utils.rx.SchedulerProviderImpl;
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

    @Provides
    @PerActivity
    TestReportPresenter<TestReportView> provideTestReportPresenter(
            TestReportPresenterImpl<TestReportView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SolutionsPresenter<SolutionsView> provideSolutionsPresenter(
            SolutionsPresenterImpl<SolutionsView> presenter) {
        return presenter;
    }
}