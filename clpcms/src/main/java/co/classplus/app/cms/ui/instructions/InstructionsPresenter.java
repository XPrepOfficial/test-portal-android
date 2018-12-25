package co.classplus.app.cms.ui.instructions;

import co.classplus.app.cms.ui.base.MvpPresenter;

public interface InstructionsPresenter<V extends InstructionsView> extends MvpPresenter<V> {

    void fetchTestInstructions(String accessToken, String testId);
}
