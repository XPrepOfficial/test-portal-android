package co.classplus.di.component;

import co.classplus.di.PerActivity;
import co.classplus.di.module.ActivityModule;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
}