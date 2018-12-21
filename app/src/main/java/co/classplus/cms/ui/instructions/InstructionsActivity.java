package co.classplus.cms.ui.instructions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.classplus.cms.R;
import co.classplus.cms.R2;
import co.classplus.cms.data.model.test.TestInstructions;
import co.classplus.cms.ui.base.BaseActivity;
import co.classplus.cms.ui.taketest.TestTakingActivity;
import co.classplus.cms.utils.StringUtils;

public class InstructionsActivity extends BaseActivity implements InstructionsView {

    public static final String PARAM_TEST_ID = "PARAM_TEST_ID";
    public static final String PARAM_CMS_ACT = "PARAM_CMS_ACT";

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.tv_test_name)
    TextView tv_test_name;
    @BindView(R2.id.tv_num_ques)
    TextView tv_num_ques;
    @BindView(R2.id.tv_test_duration)
    TextView tv_test_duration;
    @BindView(R2.id.tv_instructions)
    TextView tv_instructions;
    @BindView(R2.id.btn_attempt_test)
    Button btn_attempt_test;
    @BindView(R2.id.tv_sections_label)
    TextView tv_sections_label;
    @BindView(R2.id.rv_sections)
    RecyclerView rv_sections;

    @Inject
    InstructionsPresenter<InstructionsView> presenter;

    private String testId;
    private String cmsAccessToken;
    private SectionsAdapter sectionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        if (!getIntent().hasExtra(PARAM_TEST_ID) || !getIntent().hasExtra(PARAM_CMS_ACT)) {
            showToast("Error Loading!!");
            finish();
        } else {
            testId = getIntent().getStringExtra(PARAM_TEST_ID);
            cmsAccessToken = getIntent().getStringExtra(PARAM_CMS_ACT);
        }

        setupDependencies();
        setupUi();
    }

    private void setupDependencies() {
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.onAttach(this);
    }

    private void setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Attempt Test");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupUi() {
        setupToolbar();
        sectionsAdapter = new SectionsAdapter(this, new ArrayList<>(), false);
        rv_sections.setAdapter(sectionsAdapter);
        rv_sections.setLayoutManager(new LinearLayoutManager(this));
        ViewCompat.setNestedScrollingEnabled(rv_sections, false);
        presenter.fetchTestInstructions(cmsAccessToken, testId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R2.id.btn_attempt_test)
    public void onAttemptTestClicked() {
        startActivity(new Intent(this, TestTakingActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.onDetach();
        }
        super.onDestroy();
    }

    @Override
    public void onTestInstructionsFetched(TestInstructions testInstructions) {
        tv_test_name.setText(testInstructions.getName());
        tv_num_ques.setText(String.format(Locale.ENGLISH, "%d Questions", testInstructions.getTotalNumberOfQuestions()));
        tv_test_duration.setText(String.format(Locale.ENGLISH, "%s", StringUtils.getDurationForReports(testInstructions.getDuration())));
        tv_instructions.setText(testInstructions.getInstructions());
        if (testInstructions.getSections().size() < 2) {
            tv_sections_label.setVisibility(View.GONE);
        } else {
            tv_sections_label.setVisibility(View.VISIBLE);
            sectionsAdapter.clearSections();
            sectionsAdapter.addSections(testInstructions.getSections());
        }
    }
}
