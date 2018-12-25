package co.classplus.app.cms.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.classplus.app.cms.R;
import co.classplus.app.cms.R2;
import co.classplus.app.cms.ui.instructions.InstructionsActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R2.id.tv_html)
    HtmlTextView htmlTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        startActivity(new Intent(this, InstructionsActivity.class));
        finish();
//        String HTML_TEXT = "<p><strong>Match List - I (Event) with List-II (Order of the time interval for happening of the event) and select the correct option from the options given below the lists:</strong><img src=\\\"https://res.cloudinary.com/classplus/image/upload/v1545145303/test-portal/wcvhji0azodk1o3ycyut.png\\\" alt=\\\"\\\" /></p>\\n<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</p>";
//        htmlTextView.setHtml(HTML_TEXT, new HtmlHttpImageGetter(htmlTextView, null, true));
    }
}
