package hr.foi.challenge.challengeclient.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.challenge.challengeclient.R;
import hr.foi.challenge.challengeclient.helpers.MvpFactory;
import hr.foi.challenge.challengeclient.mvp.presenters.RegistrationPresenter;
import hr.foi.challenge.challengeclient.mvp.views.RegistrationView;

public class RegisterActivity extends BaseActivity implements RegistrationView {

    @Bind(R.id.registration_username)
    EditText registrationUsername;

    @Bind(R.id.registration_mail)
    EditText registrationMail;

    @Bind(R.id.registration_password)
    EditText registrationPassword;

    @Bind(R.id.registration_confirm_password)
    EditText registrationConfirmPassword;

    @Bind(R.id.registration_first_name)
    EditText registrationFirstName;

    @Bind(R.id.registration_last_name)
    EditText registrationLastName;

    @Bind(R.id.registration_skype)
    EditText registrationSkype;

    @Bind(R.id.confirm_register_button)
    Button confirmRegisterButton;

    private RegistrationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        presenter = MvpFactory.getPresenter(this);
    }

    @OnClick(R.id.confirm_register_button)
    protected void onRegisterClick() {
        presenter.register(registrationUsername.getText().toString(),
                            registrationMail.getText().toString(),
                            registrationPassword.getText().toString(),
                            registrationConfirmPassword.getText().toString(),
                            registrationFirstName.getText().toString(),
                            registrationLastName.getText().toString(),
                            registrationSkype.getText().toString());
    }

    @Override
    public void onRegistrationSuccess() {
        showSuccessDialog();
    }

    @Override
    public void onRegistrationFail() {
        showError(R.string.registration_error);
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

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.registration_success_title))
                .setMessage(getString(R.string.registration_success_message))
                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult();
                    }
                })
                .show();
    }

    private void sendResult() {
        setResult(RESULT_OK);
        finish();
    }
}
