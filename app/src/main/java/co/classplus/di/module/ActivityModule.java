package co.classplus.di.module;

import android.app.Activity;
import android.content.Context;

import co.classplus.di.ActivityContext;
import co.classplus.di.PerActivity;
import co.classplus.ui.base.BasePresenter;
import co.classplus.ui.base.MvpPresenter;
import co.classplus.ui.base.MvpView;
import co.classplus.ui.youtube.VideosPresenter;
import co.classplus.ui.youtube.VideosPresenterImpl;
import co.classplus.ui.youtube.VideosView;
import co.classplus.utils.rx.SchedulerProvider;
import co.classplus.utils.rx.SchedulerProviderImpl;
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
    VideosPresenter<VideosView> provideVideosPresenter(
            VideosPresenterImpl<VideosView> presenter) {
        return presenter;
    }
}