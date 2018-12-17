package co.classplus.cms.ui.taketest;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.classplus.cms.R;
import co.classplus.cms.data.model.question.SingleQuestion;
import co.classplus.cms.ui.base.BaseActivity;
import co.classplus.cms.ui.custom.ScrollCenterLayoutManager;

public class TestTakingActivity extends BaseActivity implements TestTakingView {

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
    @BindView(R.id.ll_next)
    View ll_next;
    @BindView(R.id.tv_submit)
    TextView tv_submit;

    @Inject
    TestTakingPresenter<TestTakingView> presenter;

    private QuestionsAdapter questionsAdapter;

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

        questionsAdapter = new QuestionsAdapter(this, 0, getTestData());
        rv_questions.setAdapter(questionsAdapter);
        //todo add SingleQuesFrag in the container
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
}
