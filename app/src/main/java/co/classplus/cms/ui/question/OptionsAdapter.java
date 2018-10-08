package co.classplus.cms.ui.question;

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
import co.classplus.cms.R;
import co.classplus.cms.data.model.QuestionOption;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.OptionsViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private boolean isViewingSolution;
    private ArrayList<QuestionOption> optionsList;

    public OptionsAdapter(Context context, ArrayList<QuestionOption> options) {
        this.mContext = context;
        this.optionsList = options;
        inflater = LayoutInflater.from(context);
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
        String optionHtml = "<p>" + position + 1 + "</p>" + option.getName();
        holder.tv_option.setHtml(optionHtml, holder.optionImageGetter);

        if (isViewingSolution) {
            if (option.getCorrect()) {
                holder.ll_option_root.setBackgroundResource(R.drawable.bg_round_filled_green);
                holder.tv_option.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            } else {
                if (option.getSelected()) {
                    holder.ll_option_root.setBackgroundResource(R.drawable.bg_round_empty_red);
                    holder.tv_option.setTextColor(ContextCompat.getColor(mContext, R.color.absent_red));
                } else {
                    holder.ll_option_root.setBackgroundResource(R.drawable.bg_round_empty_gray);
                    holder.tv_option.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryText));
                }
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

        @BindView(R.id.tv_option)
        HtmlTextView tv_option;
        @BindView(R.id.ll_sol)
        View ll_sol;
        @BindView(R.id.tv_sol)
        HtmlTextView tv_sol;

        View ll_option_root;

        HtmlHttpImageGetter optionImageGetter;
        HtmlHttpImageGetter solutionImageGetter;

        OptionsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ll_option_root = itemView;
            optionImageGetter = new HtmlHttpImageGetter(tv_option);
            solutionImageGetter = new HtmlHttpImageGetter(tv_sol);

            ll_option_root.setOnClickListener(v -> {
                if (getAdapterPosition() == RecyclerView.NO_POSITION) {
                    return;
                }
                if (!isViewingSolution) {
                    QuestionOption option = optionsList.get(getAdapterPosition());
                    option.setSelected(!option.getSelected());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
