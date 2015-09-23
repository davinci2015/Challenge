package hr.foi.challenge.challengeclient.activities;

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

    @Override
    public void onLoginSuccess() {
        // startActivity();
    }

    @Override
    public void onLoginFail() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(@StringRes int error) {

    }
}

