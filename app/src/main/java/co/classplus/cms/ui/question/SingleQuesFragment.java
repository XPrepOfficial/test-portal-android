package co.classplus.cms.ui.question;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.classplus.cms.R;
import co.classplus.cms.ui.base.BaseFragment;

public class SingleQuesFragment extends BaseFragment implements SingleQuesView {

    public SingleQuesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_ques, container, false);
    }

    @Override
    protected void setupUi(View view) {

    }
}
