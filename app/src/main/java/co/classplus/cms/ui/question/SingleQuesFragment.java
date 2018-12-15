package co.classplus.cms.ui.question;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.classplus.cms.R;
import co.classplus.cms.data.model.question.SingleQuestion;
import co.classplus.cms.ui.base.BaseFragment;
import co.classplus.cms.utils.StringUtils;

public class SingleQuesFragment extends BaseFragment implements SingleQuesView {

    public static final String PARAM_QUES = "PARAM_QUES";

    @BindView(R.id.ll_paragraph)
    View ll_paragraph;
    @BindView(R.id.tv_paragraph)
    HtmlTextView tv_paragraph;
    @BindView(R.id.ll_timer)
    View ll_timer;
    @BindView(R.id.tv_timer)
    TextView tv_timer;
    @BindView(R.id.ll_review)
    View ll_review;
    @BindView(R.id.tv_review)
    TextView tv_review;
    @BindView(R.id.tv_ques_text)
    HtmlTextView tv_ques_text;
    @BindView(R.id.rv_options)
    RecyclerView rv_options;

    @Inject
    SingleQuesPresenter<SingleQuesView> presenter;

    private boolean isViewingSolution;
    private SingleQuestion singleQuestion;
    private OptionsAdapter optionsAdapter;
    private long startTime, endTime;

    public static SingleQuesFragment newInstance() {
        Bundle args = new Bundle();
        SingleQuesFragment fragment = new SingleQuesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_ques, container, false);
        setupDependencies(view);
        return view;
    }

    private void setupDependencies(View view) {
        setUnBinder(ButterKnife.bind(this, view));
        getActivityComponent().inject(this);
        presenter.onAttach(this);
    }

    @Override
    protected void setupUi(View view) {
        ViewCompat.setNestedScrollingEnabled(rv_options, false);
        optionsAdapter = new OptionsAdapter(getContext(), new ArrayList<>());
        rv_options.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_options.setAdapter(optionsAdapter);
    }

    public void replaceQuestion(SingleQuestion newQuestion) {
        if (this.singleQuestion != null) {
            stopTimer();
        }
        this.singleQuestion = newQuestion;
        isViewingSolution = singleQuestion.getCorrect() != null;
        optionsAdapter.setViewingSolution(isViewingSolution);
        updateUi();
        startTimer();
    }

    public void startTimer() {
        if (this.singleQuestion != null) {
            startTime = System.currentTimeMillis();
        }
    }

    public void stopTimer() {
        if (this.singleQuestion != null) {
            endTime = System.currentTimeMillis();
            this.singleQuestion.setDuration(this.singleQuestion.getDuration() + (endTime - startTime));
        }
    }

    private void updateUi() {
        if (isViewingSolution) {
            ll_timer.setVisibility(View.VISIBLE);
            ll_review.setVisibility(View.GONE);
            tv_timer.setText(StringUtils.getDurationFromMillis(singleQuestion.getDuration()));
        } else {
            ll_timer.setVisibility(View.GONE);
            ll_review.setVisibility(View.VISIBLE);
            updateReviewState();
        }

        if (singleQuestion.getParagraph() == null) {
            ll_paragraph.setVisibility(View.GONE);
        } else {
            ll_paragraph.setVisibility(View.VISIBLE);
            tv_paragraph.setHtml(singleQuestion.getParagraph().getName(), new HtmlHttpImageGetter(tv_paragraph));
        }

        tv_ques_text.setHtml(singleQuestion.getQuesText(), new HtmlHttpImageGetter(tv_ques_text));
        optionsAdapter.clearOptions();
        optionsAdapter.addOptions(singleQuestion.getOptions());
    }

    @OnClick(R.id.ll_review)
    public void onReviewClicked() {
        if (this.singleQuestion != null) {
            singleQuestion.setMarkedForReview(!singleQuestion.isMarkedForReview());
            updateReviewState();
        }
    }

    private void updateReviewState() {
        if (this.singleQuestion != null) {
            if (singleQuestion.isMarkedForReview()) {
                tv_review.setText("Marked for review");
            } else {
                tv_review.setText("Mark for review");
            }
        }
    }

    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.onDetach();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
