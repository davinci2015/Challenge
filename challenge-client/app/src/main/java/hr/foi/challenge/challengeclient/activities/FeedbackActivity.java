package hr.foi.challenge.challengeclient.activities;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.OnClick;
import hr.foi.challenge.challengeclient.R;
import hr.foi.challenge.challengeclient.mvp.views.FeedbackView;

public class FeedbackActivity extends BaseActivity implements FeedbackView {

    @Bind(R.id.feedback_text)
    EditText feedbackText;

    @Bind(R.id.feedback_group_spinner)
    Spinner feedbackGroups;

    @Bind(R.id.feedback_button_negative)
    Button feedbackNegativeButton;

    @Bind(R.id.feedback_button_neutral)
    Button feedbackNeutralButton;

    @Bind(R.id.feedback_button_positive)
    Button feedbackPositiveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_feedback, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == (R.id.action_submit)) {
            //TODO submit
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.feedback_button_negative)
    protected void onClickButtonNegative() {
        feedbackNegativeButton.setPressed(true);
        feedbackNeutralButton.setPressed(false);
        feedbackPositiveButton.setPressed(false);
    }

    @OnClick(R.id.feedback_button_neutral)
    protected void onClickButtonNeutral() {
        feedbackNegativeButton.setPressed(false);
        feedbackNeutralButton.setPressed(true);
        feedbackPositiveButton.setPressed(false);
    }

    @OnClick(R.id.feedback_button_positive)
    protected void onClickButtonPositive() {
        feedbackNegativeButton.setPressed(false);
        feedbackNeutralButton.setPressed(false);
        feedbackPositiveButton.setPressed(true);
    }

    @Override
    public void onFeedbackSent() {

    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }

    @Override
    public void hideProgress() {
        hideProgressDialog();
    }

    @Override
    public void showError(@StringRes int error) {
        showErrorMessage(getResources().getString(error));
    }
}
