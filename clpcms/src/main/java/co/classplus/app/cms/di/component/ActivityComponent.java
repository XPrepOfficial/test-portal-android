package co.classplus.app.cms.di.component;

import co.classplus.app.cms.di.PerActivity;
import co.classplus.app.cms.di.module.ActivityModule;
import co.classplus.app.cms.ui.instructions.InstructionsActivity;
import co.classplus.app.cms.ui.question.SingleQuesFragment;
import co.classplus.app.cms.ui.report.TestReportActivity;
import co.classplus.app.cms.ui.solutions.SolutionsActivity;
import co.classplus.app.cms.ui.taketest.TestTakingActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SingleQuesFragment singleQuesFragment);

    void inject(TestTakingActivity testTakingActivity);

    void inject(InstructionsActivity instructionsActivity);

    void inject(TestReportActivity testReportActivity);

    void inject(SolutionsActivity solutionsActivity);
}