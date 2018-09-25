package co.classplus.cms.di.component;

import co.classplus.cms.di.PerActivity;
import co.classplus.cms.di.module.ActivityModule;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
}