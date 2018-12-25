package co.classplus.app.cms.ui.instructions;

import co.classplus.app.cms.data.model.test.TestInstructions;
import co.classplus.app.cms.ui.base.MvpView;

public interface InstructionsView extends MvpView {

    void onTestInstructionsFetched(TestInstructions testInstructions);
}
