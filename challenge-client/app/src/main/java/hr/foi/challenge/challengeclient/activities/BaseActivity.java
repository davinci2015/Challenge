package hr.foi.challenge.challengeclient.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import hr.foi.challenge.challengeclient.R;
import hr.foi.challenge.challengeclient.helpers.Session;

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
        snackView.setBackgroundColor(getResources().getColor(R.color.foundation));

        snackbar.show();
    }

    protected void showDialog(String message, String positive, String negative, final Activity activity) {
        // show the message box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(positive, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                new Session(activity).destroySession();
                dialog.dismiss();
                activity.finish();
            }

        });
        builder.setNegativeButton(negative, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        builder.create().show();
    }

}
