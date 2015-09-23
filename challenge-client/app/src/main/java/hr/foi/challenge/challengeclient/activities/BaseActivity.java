package hr.foi.challenge.challengeclient.activities;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toolbar;

import hr.foi.challenge.challengeclient.R;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    protected void showProgressDialog() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = ProgressDialog.show(this, null, getString(R.string.please_wait), true, false);
        }
    }

    protected void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    protected void showErrorMessage(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

        View snackView = snackbar.getView();
        snackView.setBackgroundColor(getResources().getColor(R.color.login_button_pressed));

        snackbar.show();
    }

}
