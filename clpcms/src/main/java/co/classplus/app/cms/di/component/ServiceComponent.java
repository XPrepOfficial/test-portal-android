package co.classplus.app.cms.di.component;

import co.classplus.app.cms.di.PerActivity;
import co.classplus.app.cms.di.module.ServiceModule;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
}
