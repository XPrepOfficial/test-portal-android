package co.classplus.cms.ui.instructions;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.classplus.cms.R;
import co.classplus.cms.data.model.test.TestInstructions;
import co.classplus.cms.ui.base.BaseActivity;

public class InstructionsActivity extends BaseActivity implements InstructionsView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_test_name)
    TextView tv_test_name;
    @BindView(R.id.tv_num_ques)
    TextView tv_num_ques;
    @BindView(R.id.tv_test_duration)
    TextView tv_test_duration;
    @BindView(R.id.tv_instructions)
    TextView tv_instructions;
    @BindView(R.id.btn_attempt_test)
    Button btn_attempt_test;

    @Inject
    InstructionsPresenter<InstructionsView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        setupDependencies();
        setupUi();
    }

    private void setupDependencies() {
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.onAttach(this);
    }

    private void setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Attempt Test");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupUi() {
        setupToolbar();
        presenter.fetchTestInstructions("5c13882cc3628776a4023b1e");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_attempt_test)
    public void onAttemptTestClicked() {
        //todo do something on button click
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.onDetach();
        }
        super.onDestroy();
    }

    @Override
    public void onTestInstructionsFetched(TestInstructions testInstructions) {
        tv_test_name.setText(testInstructions.getName());
        tv_num_ques.setText(String.format(Locale.ENGLISH, "%d Questions", testInstructions.getTotalNumberOfQuestions()));
        tv_test_duration.setText(String.format(Locale.ENGLISH, "%d Mins", testInstructions.getDuration()));
        tv_instructions.setText(testInstructions.getInstructions());
    }
}
