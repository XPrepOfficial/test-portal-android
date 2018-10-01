package co.classplus.cms.di.component;

import co.classplus.cms.di.PerActivity;
import co.classplus.cms.di.module.ActivityModule;
import co.classplus.cms.ui.question.SingleQuesFragment;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SingleQuesFragment singleQuesFragment);
}