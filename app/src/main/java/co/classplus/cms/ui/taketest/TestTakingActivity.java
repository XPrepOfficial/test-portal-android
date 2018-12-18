package co.classplus.cms.ui.taketest;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.classplus.cms.R;
import co.classplus.cms.data.model.base.SectionBaseModel;
import co.classplus.cms.data.model.question.SingleQuestion;
import co.classplus.cms.data.model.test.SingleTest;
import co.classplus.cms.data.model.test.TestGetResponse;
import co.classplus.cms.data.model.test.TestSection;
import co.classplus.cms.ui.base.BaseActivity;
import co.classplus.cms.ui.custom.ScrollCenterLayoutManager;
import co.classplus.cms.ui.instructions.SectionsAdapter;
import co.classplus.cms.ui.question.SingleQuesFragment;

public class TestTakingActivity extends BaseActivity implements TestTakingView, QuestionsAdapter.QuestionsListener, SectionsAdapter.SectionsListener, SingleQuesFragment.SingleQuestionListener {

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

    private Map<String, TestSection> sectionsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_taking);

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
        rv_questions.setAdapter(questionsAdapter);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        singleQuesFragment = SingleQuesFragment.newInstance();
        singleQuesFragment.setSingleQuestionListener(this);
        transaction.add(R.id.frame_ques_container, singleQuesFragment, SingleQuesFragment.TAG).commit();
        setupSectionsBottomSheet();

        sectionsMap = new HashMap<>();

        presenter.fetchTestDetails("5c192fb04c70a80b57d1221d");
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Submit your test")
                .setPositiveButton("Submit", (dialog, which) -> {
                    //todo submit test
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                });
        builder.create().show();
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
    }

    @Override
    public void onQuestionSelected(SingleQuestion singleQuestion) {
        singleQuesFragment.replaceQuestion(singleQuestion);
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
