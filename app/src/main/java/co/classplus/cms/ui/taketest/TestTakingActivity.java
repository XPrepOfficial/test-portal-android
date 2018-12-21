package co.classplus.cms.ui.taketest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.classplus.cms.R;
import co.classplus.cms.data.model.base.SectionBaseModel;
import co.classplus.cms.data.model.question.QuestionOption;
import co.classplus.cms.data.model.question.SingleQuestion;
import co.classplus.cms.data.model.submit.SubmitTestResponse;
import co.classplus.cms.data.model.test.SingleTest;
import co.classplus.cms.data.model.test.TestGetResponse;
import co.classplus.cms.data.model.test.TestSection;
import co.classplus.cms.ui.base.BaseActivity;
import co.classplus.cms.ui.custom.ScrollCenterLayoutManager;
import co.classplus.cms.ui.instructions.SectionsAdapter;
import co.classplus.cms.ui.question.SingleQuesFragment;
import co.classplus.cms.ui.report.TestReportActivity;
import co.classplus.cms.utils.StringUtils;

import static co.classplus.cms.ui.instructions.InstructionsActivity.PARAM_CMS_ACT;
import static co.classplus.cms.ui.instructions.InstructionsActivity.PARAM_TEST_ID;

public class TestTakingActivity extends BaseActivity implements TestTakingView,
        QuestionsAdapter.QuestionsListener,
        SectionsAdapter.SectionsListener,
        SingleQuesFragment.SingleQuestionListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_timer)
    View ll_timer;
    @BindView(R.id.tv_timer)
    TextView tv_timer;
    @BindView(R.id.ll_section)
    View ll_section;
    @BindView(R.id.tv_section)
    TextView tv_section;

    @BindView(R.id.rv_questions)
    RecyclerView rv_questions;
    @BindView(R.id.frame_ques_container)
    FrameLayout frame_ques_container;
    @BindView(R.id.ll_prev)
    View ll_prev;
    @BindView(R.id.iv_prev)
    ImageView iv_prev;
    @BindView(R.id.tv_prev)
    TextView tv_prev;
    @BindView(R.id.ll_next)
    View ll_next;
    @BindView(R.id.tv_submit)
    TextView tv_submit;

    @Inject
    TestTakingPresenter<TestTakingView> presenter;

    private QuestionsAdapter questionsAdapter;
    private SingleQuesFragment singleQuesFragment;
    private SectionsAdapter sectionsAdapter;
    private BottomSheetDialog sectionsBottomSheet;

    private Timer timer;
    private Handler handler;
    private String studentTestId;
    private SingleTest singleTest;
    private long startTime, endTime;
    private Map<String, TestSection> sectionsMap;
    private String testId;
    private String cmsAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_taking);

        if (!getIntent().hasExtra(PARAM_TEST_ID) || !getIntent().hasExtra(PARAM_CMS_ACT)) {
            showToast("Error Loading!!");
            finish();
        } else {
            testId = getIntent().getStringExtra(PARAM_TEST_ID);
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

    private void setupUi() {
        rv_questions.setHasFixedSize(true);
        rv_questions.setLayoutManager(new ScrollCenterLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

        questionsAdapter = new QuestionsAdapter(this, 0, new ArrayList<>(), this);
        questionsAdapter.setViewingSolution(false);
        rv_questions.setAdapter(questionsAdapter);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        singleQuesFragment = SingleQuesFragment.newInstance();
        singleQuesFragment.setSingleQuestionListener(this);
        transaction.add(R.id.frame_ques_container, singleQuesFragment, SingleQuesFragment.TAG).commit();
        setupSectionsBottomSheet();
        setupTimer();

        sectionsMap = new HashMap<>();

        presenter.fetchTestDetails(cmsAccessToken, testId, new Random().nextInt(1000) + 1);
    }

    @Override
    protected void onPause() {
        stopTimer();
        super.onPause();
    }

    @Override
    protected void onResume() {
        startTimer();
        super.onResume();
    }

    private ArrayList<SingleQuestion> getTestData() {
        ArrayList<SingleQuestion> questions = new ArrayList<>();
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        SingleQuestion question2 = new SingleQuestion();
        question2.setAttempted(true);
        questions.add(question2);
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        SingleQuestion question = new SingleQuestion();
        question.set_id("1234a");
        questions.add(question);
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        SingleQuestion question3 = new SingleQuestion();
        question3.setMarkedForReview(true);
        questions.add(question3);
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        questions.add(new SingleQuestion());
        return questions;
    }

    @OnClick(R.id.ll_section)
    public void onSectionsClicked() {
        if (sectionsBottomSheet != null) {
            sectionsBottomSheet.show();
        }
    }

    @OnClick(R.id.ll_prev)
    public void onPrevClicked() {
        questionsAdapter.onPrevClicked();
    }

    @OnClick(R.id.ll_next)
    public void onNextClicked() {
        if (sectionsAdapter.getSelectedIndex() == sectionsAdapter.getItemCount() - 1) {
            questionsAdapter.onNextClicked();
        } else {
            if (questionsAdapter.getSelectedIndex() == questionsAdapter.getItemCount() - 1) {
                sectionsAdapter.setSelectedIndex(sectionsAdapter.getSelectedIndex() + 1);
                sectionsAdapter.notifyDataSetChanged();
                onSectionSelected(sectionsAdapter.getSectionsList().get(sectionsAdapter.getSelectedIndex()));
            } else {
                questionsAdapter.onNextClicked();
            }
        }
    }

    @OnClick(R.id.tv_submit)
    public void onSubmitClicked() {
        int totalQues = 0, answeredQues = 0, markedForReview = 0;
        for (Map.Entry<String, TestSection> entry : sectionsMap.entrySet()) {
            for (SingleQuestion question : entry.getValue().getQuestions()) {
                totalQues += 1;
                for (QuestionOption option : question.getOptions()) {
                    if (option.getSelected()) {
                        answeredQues += 1;
                        break;
                    }
                }
                if (question.isMarkedForReview()) {
                    markedForReview += 1;
                }
            }
        }
        showSubmitBottomSheet(totalQues, answeredQues, markedForReview);
    }

    private void setupSectionsBottomSheet() {
        sectionsBottomSheet = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.layout_bottom_sheet_sections, null);
        RecyclerView rv_sections = view.findViewById(R.id.rv_sections);

        sectionsAdapter = new SectionsAdapter(this, new ArrayList<>(), true);
        sectionsAdapter.setSectionsListener(this);
        rv_sections.setAdapter(sectionsAdapter);
        rv_sections.setLayoutManager(new LinearLayoutManager(this));

        sectionsBottomSheet.setContentView(view);
    }

    private void showSubmitBottomSheet(int totalQuestions, int answeredQues, int markedForReview) {
        BottomSheetDialog submitBottomSheet = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.layout_bottom_sheet_submit_test, null);
        TextView tv_total_ques = view.findViewById(R.id.tv_total_ques);
        TextView tv_ques_answered = view.findViewById(R.id.tv_ques_answered);
        TextView tv_marked_for_review = view.findViewById(R.id.tv_marked_for_review);
        Button btn_sheet_submit_test = view.findViewById(R.id.btn_sheet_submit_test);

        tv_total_ques.setText(String.valueOf(totalQuestions));
        tv_ques_answered.setText(String.valueOf(answeredQues));
        tv_marked_for_review.setText(String.valueOf(markedForReview));

        btn_sheet_submit_test.setOnClickListener(v -> {
            stopTimer();
            endTime = System.currentTimeMillis();
            presenter.submitTest(cmsAccessToken, singleTest, studentTestId, endTime - startTime);
            submitBottomSheet.dismiss();
        });
        submitBottomSheet.setContentView(view);
        submitBottomSheet.show();
    }

    private void setupTimer() {
        handler = new Handler();
    }

    private void startTimer() {
        if (startTime == 0) {
            return;
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    updateTimerView();
                    checkIfTimeLeft();
                });
            }
        }, 0, 1000);
    }

    private void updateTimerView() {
        tv_timer.setText(StringUtils.getDurationFromMillis(System.currentTimeMillis() - startTime));
    }

    private void checkIfTimeLeft() {
        if (System.currentTimeMillis() - startTime > (singleTest.getDuration() + 30000)) {
            stopTimer();
            endTime = System.currentTimeMillis();
            showSubmitDialog();
        }
    }

    private void showSubmitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Time's Up")
                .setMessage("Please submit your test, time is over.")
                .setPositiveButton("Submit", (dialog, which) -> {
                    presenter.submitTest(cmsAccessToken, singleTest, studentTestId, endTime - startTime);
                    dialog.dismiss();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setCancelable(false);
        builder.create().show();
    }

    private void stopTimer() {
        timer.cancel();
        handler.removeCallbacksAndMessages(null);
    }

    private void updatePrevNextViews() {
        if (questionsAdapter.getSelectedIndex() == 0) {
            iv_prev.setColorFilter(ContextCompat.getColor(this, R.color.gray91));
            tv_prev.setTextColor(ContextCompat.getColor(this, R.color.gray91));
        } else {
            iv_prev.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary));
            tv_prev.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        if (sectionsAdapter.getSelectedIndex() == sectionsAdapter.getItemCount() - 1) {
            if (questionsAdapter.getSelectedIndex() == questionsAdapter.getItemCount() - 1) {
                ll_next.setVisibility(View.GONE);
                tv_submit.setVisibility(View.VISIBLE);
            } else {
                ll_next.setVisibility(View.VISIBLE);
                tv_submit.setVisibility(View.GONE);
            }
        } else {
            ll_next.setVisibility(View.VISIBLE);
            tv_submit.setVisibility(View.GONE);
        }
    }

    private void updateQuestionAdapter(String sectionId) {
        questionsAdapter.clearQuestions();
        questionsAdapter.addQuestions(sectionsMap.get(sectionId).getQuestions());
        questionsAdapter.setSelectedQuestionId(sectionsMap.get(sectionId).getQuestions().get(0).get_id());
        questionsAdapter.setSelectedIndex(0);
        onQuestionSelected(sectionsMap.get(sectionId).getQuestions().get(0));
    }

    @Override
    public void onTestDetailsFetched(TestGetResponse testGetResponse) {
        SingleTest singleTest = testGetResponse.getData().getTest();
        this.singleTest = singleTest;
        this.studentTestId = testGetResponse.getData().getStudentTestId();
        ArrayList<SectionBaseModel> sectionsList = new ArrayList<>();
        for (TestSection testSection : singleTest.getSections()) {
            sectionsMap.put(testSection.get_id(), testSection);
            sectionsList.add(new SectionBaseModel(testSection));
        }
        sectionsAdapter.clearSections();
        sectionsAdapter.addSections(sectionsList);
        sectionsAdapter.setSelectedIndex(0);
        tv_section.setText(String.format(Locale.ENGLISH, "Section-%d", sectionsList.get(0).getOrder()));
        updateQuestionAdapter(sectionsList.get(0).get_id());

        if (singleTest.getSections().size() < 2) {
            ll_section.setVisibility(View.GONE);
        } else {
            ll_section.setVisibility(View.VISIBLE);
        }
        updatePrevNextViews();
        startTime = System.currentTimeMillis();
        startTimer();
    }

    @Override
    public void onTestSubmitSuccess(SubmitTestResponse.SubmitTestData data) {
        startActivity(new Intent(this, TestReportActivity.class)
                .putExtra(TestReportActivity.PARAM_TIME_TAKEN, data.getTimeTaken())
                .putExtra(TestReportActivity.PARAM_CORRECT_ANSWERS, data.getCorrectAnswers())
                .putExtra(TestReportActivity.PARAM_INCORRECT_ANSWERS, data.getNumberOfQuestionsAttempted() - data.getCorrectAnswers())
                .putExtra(TestReportActivity.PARAM_UNANSWERED_ANSWERS, data.getTotalQuestion() - data.getNumberOfQuestionsAttempted())
                .putExtra(TestReportActivity.PARAM_TEST_ID, singleTest.get_id())
                .putExtra(TestReportActivity.PARAM_STUDENT_TEST_ID, studentTestId));
        finish();
    }

    @Override
    public void onTestSubmitError() {
        showSnackBar("Error!! Please try again...");
    }

    @Override
    public void onQuestionSelected(SingleQuestion singleQuestion) {
        singleQuesFragment.replaceQuestion(singleQuestion, false);
//        rv_questions.smoothScrollToPosition(questionsAdapter.getSelectedIndex());
        updatePrevNextViews();
    }

    @Override
    public void onSectionSelected(SectionBaseModel sectionBaseModel) {
        sectionsBottomSheet.dismiss();
        tv_section.setText(String.format(Locale.ENGLISH, "Section-%d", sectionBaseModel.getOrder()));
        updateQuestionAdapter(sectionBaseModel.get_id());
        updatePrevNextViews();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.onDetach();
        }
        super.onDestroy();
    }

    @Override
    public void onMarkForReviewChange() {
        questionsAdapter.notifyDataSetChanged();
    }
}
