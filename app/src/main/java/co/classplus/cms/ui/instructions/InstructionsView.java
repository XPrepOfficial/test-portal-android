package co.classplus.cms.ui.instructions;

import co.classplus.cms.data.model.test.TestInstructions;
import co.classplus.cms.ui.base.MvpView;

public interface InstructionsView extends MvpView {

    void onTestInstructionsFetched(TestInstructions testInstructions);
}
