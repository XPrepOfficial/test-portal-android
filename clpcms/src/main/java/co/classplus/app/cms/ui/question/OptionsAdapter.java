package co.classplus.app.cms.ui.question;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.classplus.app.cms.R;
import co.classplus.app.cms.R2;
import co.classplus.app.cms.data.model.question.QuestionOption;
import co.classplus.app.cms.data.model.question.SingleQuestion;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.OptionsViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private boolean isViewingSolution;
    private SingleQuestion singleQuestion;
    private ArrayList<QuestionOption> optionsList;

    public OptionsAdapter(Context context, ArrayList<QuestionOption> options) {
        this.mContext = context;
        this.optionsList = options;
        inflater = LayoutInflater.from(context);
    }

    public SingleQuestion getSingleQuestion() {
        return singleQuestion;
    }

    public void setSingleQuestion(SingleQuestion singleQuestion) {
        this.singleQuestion = singleQuestion;
    }

    public boolean isViewingSolution() {
        return isViewingSolution;
    }

    public void setViewingSolution(boolean viewingSolution) {
        isViewingSolution = viewingSolution;
    }

    void addOptions(ArrayList<QuestionOption> options) {
        optionsList.addAll(options);
        notifyDataSetChanged();
    }

    void clearOptions() {
        optionsList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OptionsViewHolder(inflater.inflate(R.layout.item_options, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OptionsViewHolder holder, int position) {
        QuestionOption option = optionsList.get(position);
        String optionHtml = "<span style=\"display: inline;\">" + String.valueOf(position + 1) + option.getName() + "</span>";
        holder.tv_option.setHtml(optionHtml, holder.optionImageGetter);

        if (isViewingSolution) {
            if (option.getCorrect()) {
                holder.ll_option_root.setBackgroundResource(R.drawable.bg_round_filled_green);
                holder.tv_option.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                if (this.singleQuestion != null) {
                    holder.ll_sol.setVisibility(View.VISIBLE);
                    holder.tv_sol.setHtml(singleQuestion.getSolution(), holder.solutionImageGetter);
                } else {
                    holder.ll_sol.setVisibility(View.GONE);
                }
            } else {
                if (option.getSelected()) {
                    holder.ll_option_root.setBackgroundResource(R.drawable.bg_round_empty_red);
                    holder.tv_option.setTextColor(ContextCompat.getColor(mContext, R.color.absent_red));
                } else {
                    holder.ll_option_root.setBackgroundResource(R.drawable.bg_round_empty_gray);
                    holder.tv_option.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryText));
                }
                holder.ll_sol.setVisibility(View.GONE);
            }
        } else {
            if (option.getSelected()) {
                holder.ll_option_root.setBackgroundResource(R.drawable.bg_round_filled_blue);
                holder.tv_option.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryText));
            } else {
                holder.ll_option_root.setBackgroundResource(R.drawable.bg_round_empty_gray);
                holder.tv_option.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryText));
            }
        }
    }

    @Override
    public int getItemCount() {
        return optionsList.size();
    }

    class OptionsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.tv_option)
        HtmlTextView tv_option;
        @BindView(R2.id.ll_sol)
        View ll_sol;
        @BindView(R2.id.tv_sol)
        HtmlTextView tv_sol;
        @BindView(R2.id.ll_option_root)
        View ll_option_root;

        HtmlHttpImageGetter optionImageGetter;
        HtmlHttpImageGetter solutionImageGetter;

        OptionsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            optionImageGetter = new HtmlHttpImageGetter(tv_option, null, true);
            solutionImageGetter = new HtmlHttpImageGetter(tv_sol, null, true);
        }

        @OnClick({R2.id.ll_option_root, R2.id.tv_option})
        public void onRootViewClicked() {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) {
                return;
            }
            if (!isViewingSolution) {
                QuestionOption option = optionsList.get(getAdapterPosition());
                if (!singleQuestion.isHasMultipleAnswer()) {
                    for (QuestionOption questionOption : optionsList) {
                        if (questionOption.get_id().equals(option.get_id())) {
                            questionOption.setSelected(!questionOption.getSelected());
                        } else {
                            questionOption.setSelected(false);
                        }
                    }
                } else {
                    option.setSelected(!option.getSelected());
                }
                notifyDataSetChanged();
            }
        }
    }
}
