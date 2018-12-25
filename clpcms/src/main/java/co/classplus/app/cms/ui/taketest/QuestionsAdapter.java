package co.classplus.app.cms.ui.taketest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.classplus.app.cms.R;
import co.classplus.app.cms.R2;
import co.classplus.app.cms.data.model.question.SingleQuestion;
import co.classplus.app.cms.ui.base.BaseViewHolder;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

    private Context context;
    private int offset;
    private int selectedIndex;
    private boolean isViewingSolution;
    private String selectedQuestionId = "1234a";
    private ArrayList<SingleQuestion> questions;
    private QuestionsListener questionsListener;

    public QuestionsAdapter(Context context, int offset, ArrayList<SingleQuestion> questions, QuestionsListener questionsListener) {
        this.context = context;
        this.offset = offset;
        this.questions = questions;
        this.questionsListener = questionsListener;
    }

    public void clearQuestions() {
        this.questions.clear();
        notifyDataSetChanged();
    }

    public void addQuestions(ArrayList<SingleQuestion> questions) {
        this.questions.addAll(questions);
        notifyDataSetChanged();
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public String getSelectedQuestionId() {
        return selectedQuestionId;
    }

    public void setSelectedQuestionId(String selectedQuestionId) {
        this.selectedQuestionId = selectedQuestionId;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public boolean isViewingSolution() {
        return isViewingSolution;
    }

    public void setViewingSolution(boolean viewingSolution) {
        isViewingSolution = viewingSolution;
    }

    public void onPrevClicked() {
        if (selectedIndex > 0) {
            selectedIndex -= 1;
            selectedQuestionId = questions.get(selectedIndex).get_id();
            questionsListener.onQuestionSelected(questions.get(selectedIndex));
            notifyDataSetChanged();
        }
    }

    public void onNextClicked() {
        if (selectedIndex < getItemCount() - 1) {
            selectedIndex += 1;
            selectedQuestionId = questions.get(selectedIndex).get_id();
            questionsListener.onQuestionSelected(questions.get(selectedIndex));
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_number, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        SingleQuestion question = questions.get(position);
        holder.tv_number.setText(String.valueOf(offset + position + 1));
        if (!isViewingSolution) {
            if (question.isMarkedForReview()) {
                holder.rl_marked_review.setVisibility(View.VISIBLE);
            } else {
                holder.rl_marked_review.setVisibility(View.GONE);
            }
        } else {
            holder.rl_marked_review.setVisibility(View.GONE);
        }
        if (selectedQuestionId.equals(question.get_id())) {
            holder.rl_selected.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_selected_ques));
            holder.tv_number.setTextColor(ContextCompat.getColor(context, R.color.white));
        } else {
            holder.rl_selected.setBackground(null);
            if (isViewingSolution) {
                if (question.isAttempted()) {
                    if (question.getCorrect()) {
                        holder.tv_number.setTextColor(ContextCompat.getColor(context, R.color.present_green));
                    } else {
                        holder.tv_number.setTextColor(ContextCompat.getColor(context, R.color.absent_red));
                    }
                } else {
                    holder.tv_number.setTextColor(ContextCompat.getColor(context, R.color.colorSecondaryText));
                }
            } else {
                holder.tv_number.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryText));
            }
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class QuestionViewHolder extends BaseViewHolder implements View.OnClickListener {

        @BindView(R2.id.rl_selected)
        RelativeLayout rl_selected;
        @BindView(R2.id.tv_number)
        TextView tv_number;
        @BindView(R2.id.rl_marked_review)
        RelativeLayout rl_marked_review;

        QuestionViewHolder(View itemView) {
            super(context, itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                selectedIndex = getAdapterPosition();
                selectedQuestionId = questions.get(getAdapterPosition()).get_id();
                questionsListener.onQuestionSelected(questions.get(getAdapterPosition()));
                notifyDataSetChanged();
            }
        }
    }

    public interface QuestionsListener {

        void onQuestionSelected(SingleQuestion singleQuestion);
    }
}
