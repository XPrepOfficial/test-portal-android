package co.classplus.cms.di.component;

import co.classplus.cms.di.PerActivity;
import co.classplus.cms.di.module.ServiceModule;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
}
