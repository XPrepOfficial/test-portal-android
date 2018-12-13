package co.classplus.cms.ui.taketest;

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
import co.classplus.cms.R;
import co.classplus.cms.data.model.SingleQuestion;
import co.classplus.cms.ui.base.BaseViewHolder;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

    private Context context;
    private int offset;
    private ArrayList<SingleQuestion> questions;
    private String selectedQuestionId = "1234a";

    public QuestionsAdapter(Context context, int offset, ArrayList<SingleQuestion> questions) {
        this.context = context;
        this.offset = offset;
        this.questions = questions;
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
        if (question.isMarkedForReview()) {
            holder.rl_marked_review.setVisibility(View.VISIBLE);
        } else {
            holder.rl_marked_review.setVisibility(View.GONE);
        }
        if (selectedQuestionId.equals(question.get_id())) {
            holder.rl_selected.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_selected_ques));
            holder.tv_number.setTextColor(ContextCompat.getColor(context, R.color.white));
        } else {
            holder.rl_selected.setBackground(null);
            if (question.isAttempted()) {
                holder.tv_number.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            } else {
                holder.tv_number.setTextColor(ContextCompat.getColor(context, R.color.colorSecondaryText));
            }
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class QuestionViewHolder extends BaseViewHolder implements View.OnClickListener {

        @BindView(R.id.rl_selected)
        RelativeLayout rl_selected;
        @BindView(R.id.tv_number)
        TextView tv_number;
        @BindView(R.id.rl_marked_review)
        RelativeLayout rl_marked_review;

        QuestionViewHolder(View itemView) {
            super(context, itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
