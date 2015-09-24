package hr.foi.challenge.challengeclient.activities;

import android.os.AsyncTask;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.challenge.challengeclient.FeedbackApplication;
import hr.foi.challenge.challengeclient.R;
import hr.foi.challenge.challengeclient.adapters.GroupAdapter;
import hr.foi.challenge.challengeclient.helpers.MvpFactory;
import hr.foi.challenge.challengeclient.helpers.ServiceCaller;
import hr.foi.challenge.challengeclient.helpers.Session;
import hr.foi.challenge.challengeclient.models.Group;
import hr.foi.challenge.challengeclient.mvp.presenters.FeedbackPresenter;
import hr.foi.challenge.challengeclient.mvp.views.FeedbackView;

public class FeedbackActivity extends BaseActivity implements FeedbackView {

    private static int NEGATIVE_FEEDBACK = -1;

    private static int NEUTRAL_FEEDBACK = 0;

    private static int POSITIVE_FEEDBACK = 1;

    boolean empty;

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

    @Bind(R.id.project_title)
    TextView projectTitle;

    private FeedbackPresenter presenter;

    private List<Group> groups;

    private String selectedGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);

        presenter = MvpFactory.getPresenter(this);
        //initGroupSpinner();
        showProgress();
        new GroupFetchTask().execute();
        hideProgress();
        projectTitle.setText(new Session(FeedbackApplication.getInstance()).retrieveProjectTitle());
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
            String groupName = "";
            if(!empty) groupName = ((Group)feedbackGroupsSpinner.getSelectedItem()).getName();
            presenter.sendFeedback(feedbackText.getText().toString(), groupName);
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
        showSuccessDialog();
    }

    @Override
    public void onGroupsReceived(List<Group> groups) {
        this.groups = groups;

// use default spinner item to show options in spinner
        ArrayAdapter<Group> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,groups);
        try {
            feedbackGroupsSpinner.setAdapter(adapter);
            empty = false;
        } catch(Exception e) {
            empty = true;
            showErrorMessage("There are no groups for this project");
        }
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

        feedbackGroupsSpinner.setAdapter(new GroupAdapter(this, groups));
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

    private class GroupFetchTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String json = "";
            try {
                json = ServiceCaller.call(new URL("http://46.101.207.199/challenge/get_groups.php?project_id="
                        + String.valueOf(new Session(FeedbackApplication.getInstance()).retrieveProjectID())), "GET");
            } catch (IOException e) {
                showErrorMessage("Service call fail");
            }

            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            onGroupsReceived((List<Group>)new Gson().fromJson(json, new TypeToken<List<Group>>(){}.getType()));
        }
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.registration_success_title))
                .setMessage(getString(R.string.feedback_success))
                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onEndActivity();
                    }
                })
                .show();
    }

    private void onEndActivity() {
        finish();
    }
}
