package co.classplus.cms.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import co.classplus.cms.App;
import co.classplus.cms.data.DataManager;
import co.classplus.cms.di.ApplicationContext;
import co.classplus.cms.di.module.ApplicationModule;
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
