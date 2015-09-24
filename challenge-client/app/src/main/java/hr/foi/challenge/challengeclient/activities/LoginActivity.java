package hr.foi.challenge.challengeclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.challenge.challengeclient.R;
import hr.foi.challenge.challengeclient.helpers.MvpFactory;
import hr.foi.challenge.challengeclient.mvp.presenters.LoginPresenter;
import hr.foi.challenge.challengeclient.mvp.views.LoginView;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginView {

    public static final int REGISTRATION_REQUEST = 1;

    @Bind(R.id.sign_in_button) Button signIn;
    @Bind(R.id.register_button) Button register;
    @Bind(R.id.username) EditText username;
    @Bind(R.id.password) EditText password;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        presenter = MvpFactory.getPresenter(this);
    }

    @OnClick(R.id.sign_in_button)
    protected void onClickLogin() {
        presenter.login(username.getText().toString(), password.getText().toString());
    }

    @OnClick(R.id.register_button)
    protected void onClickRegister() {
        startActivityForResult(new Intent(this, RegisterActivity.class), REGISTRATION_REQUEST);
    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(this, ProjectListActivity.class));
        finish();
    }

    @Override
    public void onLoginFail() {
        showError(R.string.incorrect_credentials);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REGISTRATION_REQUEST && resultCode == RESULT_OK) {
            onLoginSuccess();
        }
    }
}

