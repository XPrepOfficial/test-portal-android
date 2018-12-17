package co.classplus.cms.ui.instructions;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.classplus.cms.R;
import co.classplus.cms.data.model.base.SectionBaseModel;

public class SectionsAdapter extends RecyclerView.Adapter<SectionsAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<SectionBaseModel> sectionsList;

    public SectionsAdapter(Context context, ArrayList<SectionBaseModel> sectionsList) {
        mContext = context;
        this.sectionsList = sectionsList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_section, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SectionBaseModel section = sectionsList.get(position);
    }

    @Override
    public int getItemCount() {
        return sectionsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_index)
        TextView tv_index;
        @BindView(R.id.tv_section_name)
        TextView tv_section_name;
        @BindView(R.id.tv_num_ques)
        TextView tv_num_ques;
        @BindView(R.id.iv_selected)
        ImageView iv_selected;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
