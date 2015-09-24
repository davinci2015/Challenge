package hr.foi.challenge.challengeclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.challenge.challengeclient.FeedbackApplication;
import hr.foi.challenge.challengeclient.R;
import hr.foi.challenge.challengeclient.helpers.MvpFactory;
import hr.foi.challenge.challengeclient.helpers.Session;
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
    @Bind(R.id.logoImage)
    ImageView logo;
    @Bind(R.id.firstTIL)
    TextInputLayout first;
    @Bind(R.id.secondTIL) TextInputLayout second;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        Session session = new Session(FeedbackApplication.getInstance());
        if(session.retrieveSession() != null) {
            onLoginSuccess();
        }

        presenter = MvpFactory.getPresenter(this);
        startAnimation();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void startAnimation() {
        Animation moveAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_logo);
        Animation fadeAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.show_login);
        logo.startAnimation(moveAnimation);
        signIn.startAnimation(fadeAnimation);
        register.startAnimation(fadeAnimation);
        username.startAnimation(fadeAnimation);
        password.startAnimation(fadeAnimation);
        first.startAnimation(fadeAnimation);
        second.startAnimation(fadeAnimation);
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

