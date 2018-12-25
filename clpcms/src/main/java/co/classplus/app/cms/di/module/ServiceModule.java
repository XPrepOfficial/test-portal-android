package co.classplus.app.cms.di.module;

import android.app.Service;
import android.content.Context;

import co.classplus.app.cms.di.ServiceContext;
import co.classplus.app.cms.utils.rx.SchedulerProvider;
import co.classplus.app.cms.utils.rx.SchedulerProviderImpl;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ServiceModule {

    private Service service;

    public ServiceModule(Service service) {
        this.service = service;
    }

    @Provides
    @ServiceContext
    Context provideContext() {
        return service;
    }

    @Provides
    Service provideService() {
        return service;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new SchedulerProviderImpl();
    }
}
