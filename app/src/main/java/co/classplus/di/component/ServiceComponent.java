package co.classplus.di.component;

import co.classplus.di.PerActivity;
import co.classplus.di.module.ServiceModule;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
}
