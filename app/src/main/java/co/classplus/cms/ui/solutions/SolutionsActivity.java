package co.classplus.cms.ui.solutions;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.classplus.cms.R;
import co.classplus.cms.R2;
import co.classplus.cms.data.model.question.SingleQuestion;
import co.classplus.cms.data.model.solutions.TestSolutionResponse;
import co.classplus.cms.ui.base.BaseActivity;
import co.classplus.cms.ui.custom.ScrollCenterLayoutManager;
import co.classplus.cms.ui.question.SingleQuesFragment;
import co.classplus.cms.ui.taketest.QuestionsAdapter;

import static co.classplus.cms.ui.instructions.InstructionsActivity.PARAM_CMS_ACT;
import static co.classplus.cms.ui.report.TestReportActivity.PARAM_STUDENT_TEST_ID;
import static co.classplus.cms.ui.report.TestReportActivity.PARAM_TEST_ID;

public class SolutionsActivity extends BaseActivity implements SolutionsView,
        QuestionsAdapter.QuestionsListener, SingleQuesFragment.SingleQuestionListener {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.rv_questions)
    RecyclerView rv_questions;
    @BindView(R2.id.frame_ques_container)
    FrameLayout frame_ques_container;
    @BindView(R2.id.ll_prev)
    View ll_prev;
    @BindView(R2.id.iv_prev)
    ImageView iv_prev;
    @BindView(R2.id.tv_prev)
    TextView tv_prev;
    @BindView(R2.id.ll_next)
    View ll_next;

    @Inject
    SolutionsPresenter<SolutionsView> presenter;

    private QuestionsAdapter questionsAdapter;
    private SingleQuesFragment singleQuesFragment;
    private String testId;
    private String studentTestId;
    private String cmsAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions);

        if (!getIntent().hasExtra(PARAM_TEST_ID) || !getIntent().hasExtra(PARAM_STUDENT_TEST_ID)
                || !getIntent().hasExtra(PARAM_CMS_ACT)) {
            showToast("Error loading data!!");
            finish();
        } else {
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
        getSupportActionBar().setTitle("Solutions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupUi() {
        setupToolbar();
        rv_questions.setHasFixedSize(true);
        rv_questions.setLayoutManager(new ScrollCenterLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

        questionsAdapter = new QuestionsAdapter(this, 0, new ArrayList<>(), this);
        questionsAdapter.setViewingSolution(true);
        rv_questions.setAdapter(questionsAdapter);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        singleQuesFragment = SingleQuesFragment.newInstance();
        singleQuesFragment.setSingleQuestionListener(this);
        transaction.add(R.id.frame_ques_container, singleQuesFragment, SingleQuesFragment.TAG).commit();

        presenter.fetchTestSolutions(cmsAccessToken, testId, studentTestId);
    }

    @OnClick(R2.id.ll_prev)
    public void onPrevClicked() {
        questionsAdapter.onPrevClicked();
    }

    @OnClick(R2.id.ll_next)
    public void onNextClicked() {
        questionsAdapter.onNextClicked();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.onDetach();
        }
        super.onDestroy();
    }

    @Override
    public void onQuestionSelected(SingleQuestion singleQuestion) {
        singleQuesFragment.replaceQuestion(singleQuestion, true);
//        rv_questions.smoothScrollToPosition(questionsAdapter.getSelectedIndex());
    }

    @Override
    public void onMarkForReviewChange() {
        // do nothing here
    }

    @Override
    public void onTestSolutionsFetched(TestSolutionResponse.TestSolutionData testSolutionData) {
        questionsAdapter.clearQuestions();
        questionsAdapter.addQuestions(testSolutionData.getQuestions());
        questionsAdapter.setSelectedQuestionId(testSolutionData.getQuestions().get(0).get_id());
        questionsAdapter.setSelectedIndex(0);
        onQuestionSelected(testSolutionData.getQuestions().get(0));
    }
}
