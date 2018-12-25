package co.classplus.app.cms.ui.instructions;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.classplus.app.cms.R;
import co.classplus.app.cms.data.model.base.SectionBaseModel;
import co.classplus.app.cms.utils.ViewUtils;

public class SectionsAdapter extends RecyclerView.Adapter<SectionsAdapter.ViewHolder> {

    private int selectedIndex;
    private LayoutInflater inflater;
    private boolean isSelectionEnabled;
    private SectionsListener sectionsListener;
    private ArrayList<SectionBaseModel> sectionsList;

    public SectionsAdapter(Context context, ArrayList<SectionBaseModel> sectionsList, boolean isSelectionEnabled) {
        this.sectionsList = sectionsList;
        inflater = LayoutInflater.from(context);
        this.isSelectionEnabled = isSelectionEnabled;
    }

    public void clearSections() {
        this.sectionsList.clear();
        notifyDataSetChanged();
    }

    public void addSections(ArrayList<SectionBaseModel> sections) {
        this.sectionsList.addAll(sections);
        notifyDataSetChanged();
    }

    public ArrayList<SectionBaseModel> getSectionsList() {
        return sectionsList;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public void setSectionsListener(SectionsListener sectionsListener) {
        this.sectionsListener = sectionsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_section, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SectionBaseModel section = sectionsList.get(position);
        ViewUtils.setCircleImageWithDrawable(holder.iv_index, null, String.valueOf(position + 1));
        holder.tv_section_name.setText(section.getName());
        holder.tv_num_ques.setText(String.format(Locale.ENGLISH, "%d Questions", section.getNumberOfQuestions()));
        if (isSelectionEnabled) {
            if (position == selectedIndex) {
                holder.iv_selected.setVisibility(View.VISIBLE);
            } else {
                holder.iv_selected.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return sectionsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_index)
        CircularImageView iv_index;
        @BindView(R.id.tv_section_name)
        TextView tv_section_name;
        @BindView(R.id.tv_num_ques)
        TextView tv_num_ques;
        @BindView(R.id.iv_selected)
        ImageView iv_selected;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> {
                if (isSelectionEnabled && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    selectedIndex = getAdapterPosition();
                    if (sectionsListener != null) {
                        sectionsListener.onSectionSelected(sectionsList.get(getAdapterPosition()));
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }

    public interface SectionsListener {

        void onSectionSelected(SectionBaseModel sectionBaseModel);
    }
}
