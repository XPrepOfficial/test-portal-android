package co.classplus.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import co.classplus.App;
import co.classplus.data.DataManager;
import co.classplus.di.ApplicationContext;
import co.classplus.di.module.ApplicationModule;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
