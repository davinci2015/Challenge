package hr.foi.challenge.challengeclient.activities;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hr.foi.challenge.challengeclient.R;
import hr.foi.challenge.challengeclient.adapters.ProjectAdapter;
import hr.foi.challenge.challengeclient.models.Feedback;
import hr.foi.challenge.challengeclient.mvp.presenters.ProjectPresenter;
import hr.foi.challenge.challengeclient.mvp.views.ProjectView;

public class ProjectActivity extends BaseActivity implements ProjectView {

    @Bind(R.id.listView)
    ListView view;

    ProjectPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_project, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadFeedback(getIntent().getExtras().getInt("project"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onReceived(List<Feedback> projectList) {
        view.setAdapter(new ProjectAdapter(this, projectList));
    }

    @Override
    public void onReceivedFailed() {

    }

    @Override
    public void onReceivedEmpty() {

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
