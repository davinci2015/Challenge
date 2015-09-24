package hr.foi.challenge.challengeclient.activities;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.challenge.challengeclient.R;
import hr.foi.challenge.challengeclient.helpers.MvpFactory;
import hr.foi.challenge.challengeclient.mvp.presenters.FeedbackPresenter;
import hr.foi.challenge.challengeclient.mvp.views.FeedbackView;

public class FeedbackActivity extends BaseActivity implements FeedbackView {

    private static int NEGATIVE_FEEDBACK = -1;

    private static int NEUTRAL_FEEDBACK = 0;

    private static int POSITIVE_FEEDBACK = 1;

    @Bind(R.id.feedback_text)
    EditText feedbackText;

    @Bind(R.id.feedback_group_spinner)
    Spinner feedbackGroupsSpinner;

    @Bind(R.id.feedback_button_negative)
    Button feedbackNegativeButton;

    @Bind(R.id.feedback_button_neutral)
    Button feedbackNeutralButton;

    @Bind(R.id.feedback_button_positive)
    Button feedbackPositiveButton;

    private FeedbackPresenter presenter;

    private List<String> groups;

    private String selectedGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);

        presenter = MvpFactory.getPresenter(this);
        initGroupSpinner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_feedback, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == (R.id.action_submit)) {
            //TODO submit multimedia
            presenter.sendFeedback(feedbackText.getText().toString());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.feedback_button_negative)
    protected void onClickButtonNegative() {
        presenter.changeFeedbackType(NEGATIVE_FEEDBACK);
    }

    @OnClick(R.id.feedback_button_neutral)
    protected void onClickButtonNeutral() {
        presenter.changeFeedbackType(NEUTRAL_FEEDBACK);
    }

    @OnClick(R.id.feedback_button_positive)
    protected void onClickButtonPositive() {
        presenter.changeFeedbackType(POSITIVE_FEEDBACK);
    }

    @Override
    public void onFeedbackSent() {

    }

    @Override
    public void onGroupsReceived(List<String> groups) {
        this.groups = groups;
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

    private void initGroupSpinner() {
        presenter.getGroups();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        feedbackGroupsSpinner.setAdapter(adapter);
    }

    AdapterView.OnItemSelectedListener groupSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedGroup = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
