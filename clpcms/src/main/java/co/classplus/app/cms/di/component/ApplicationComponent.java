package co.classplus.app.cms.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import co.classplus.app.cms.App;
import co.classplus.app.cms.data.DataManager;
import co.classplus.app.cms.di.ApplicationContext;
import co.classplus.app.cms.di.module.ApplicationModule;
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
