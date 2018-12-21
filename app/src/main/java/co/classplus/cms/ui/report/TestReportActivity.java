package co.classplus.cms.ui.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.classplus.cms.R;
import co.classplus.cms.R2;
import co.classplus.cms.ui.base.BaseActivity;
import co.classplus.cms.ui.solutions.SolutionsActivity;
import co.classplus.cms.utils.StringUtils;

import static co.classplus.cms.ui.instructions.InstructionsActivity.PARAM_CMS_ACT;

public class TestReportActivity extends BaseActivity implements TestReportView {

    public static final String PARAM_TIME_TAKEN = "PARAM_TIME_TAKEN";
    public static final String PARAM_CORRECT_ANSWERS = "PARAM_CORRECT_ANSWERS";
    public static final String PARAM_INCORRECT_ANSWERS = "PARAM_INCORRECT_ANSWERS";
    public static final String PARAM_UNANSWERED_ANSWERS = "PARAM_UNANSWERED_ANSWERS";
    public static final String PARAM_TEST_ID = "PARAM_TEST_ID";
    public static final String PARAM_STUDENT_TEST_ID = "PARAM_STUDENT_TEST_ID";

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.tv_time_taken)
    TextView tv_time_taken;
    @BindView(R2.id.tv_correct_answers)
    TextView tv_correct_answers;
    @BindView(R2.id.tv_incorrect_answers)
    TextView tv_incorrect_answers;
    @BindView(R2.id.tv_unanswered_answers)
    TextView tv_unanswered_answers;
    @BindView(R2.id.ll_check_solutions)
    View ll_check_solutions;

    @Inject
    TestReportPresenter<TestReportView> presenter;

    private long timeTaken;
    private int correctAnswers, incorrectAnswers, unansweredAnswers;
    private String testId;
    private String studentTestId;
    private String cmsAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_report);

        if (!getIntent().hasExtra(PARAM_TIME_TAKEN) || !getIntent().hasExtra(PARAM_CORRECT_ANSWERS)
                || !getIntent().hasExtra(PARAM_INCORRECT_ANSWERS) || !getIntent().hasExtra(PARAM_UNANSWERED_ANSWERS)
                || !getIntent().hasExtra(PARAM_TEST_ID) || !getIntent().hasExtra(PARAM_STUDENT_TEST_ID)
                || !getIntent().hasExtra(PARAM_CMS_ACT)) {
            showToast("Error loading data!!");
            finish();
        } else {
            timeTaken = getIntent().getLongExtra(PARAM_TIME_TAKEN, 0);
            correctAnswers = getIntent().getIntExtra(PARAM_CORRECT_ANSWERS, 0);
            incorrectAnswers = getIntent().getIntExtra(PARAM_INCORRECT_ANSWERS, 0);
            unansweredAnswers = getIntent().getIntExtra(PARAM_UNANSWERED_ANSWERS, 0);
            testId = getIntent().getStringExtra(PARAM_TEST_ID);
            studentTestId = getIntent().getStringExtra(PARAM_STUDENT_TEST_ID);
            cmsAccessToken = getIntent().getStringExtra(PARAM_CMS_ACT);
        }

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
        getSupportActionBar().setTitle("Test Report");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupUi() {
        setupToolbar();
        tv_time_taken.setText(String.format(Locale.ENGLISH, "Test completed in %s", StringUtils.getDurationForReports(timeTaken)));
        tv_correct_answers.setText(String.format(Locale.ENGLISH, "%d Correct Answers", correctAnswers));
        tv_incorrect_answers.setText(String.format(Locale.ENGLISH, "%d Incorrect Answers", incorrectAnswers));
        tv_unanswered_answers.setText(String.format(Locale.ENGLISH, "%d Unanswered Question", unansweredAnswers));
    }

    @OnClick(R2.id.btn_check_solutions)
    public void onCheckSolutionsClicked() {
        startActivity(new Intent(this, SolutionsActivity.class)
                .putExtra(PARAM_TEST_ID, testId)
                .putExtra(PARAM_STUDENT_TEST_ID, studentTestId)
                .putExtra(PARAM_CMS_ACT, cmsAccessToken));
        finish();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.onDetach();
        }
        super.onDestroy();
    }
}
