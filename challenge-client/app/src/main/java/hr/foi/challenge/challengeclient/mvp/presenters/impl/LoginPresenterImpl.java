package hr.foi.challenge.challengeclient.mvp.presenters.impl;

import android.text.TextUtils;

import hr.foi.challenge.challengeclient.FeedbackApplication;
import hr.foi.challenge.challengeclient.R;
import hr.foi.challenge.challengeclient.helpers.Session;
import hr.foi.challenge.challengeclient.models.User;
import hr.foi.challenge.challengeclient.mvp.interactors.LoginInteractor;
import hr.foi.challenge.challengeclient.mvp.listeners.LoginListener;
import hr.foi.challenge.challengeclient.mvp.presenters.LoginPresenter;
import hr.foi.challenge.challengeclient.mvp.views.LoginView;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public class LoginPresenterImpl implements LoginPresenter {

    LoginView view;
    LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view, LoginInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void login(String username, String password) {
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            view.showError(R.string.empty_credentials_error);
        } else if(username.length() < 5) {
            view.showError(R.string.username_error);
        } else if(password.length() < 6) {
            view.showError(R.string.password_error);
        } else {
            view.showProgress();
            interactor.login(listener, username, password);
        }
    }

    private LoginListener listener = new LoginListener() {

        @Override
        public void onLoginSuccess(User user) {
            view.hideProgress();
            view.onLoginSuccess();
            new Session(FeedbackApplication.getInstance()).createSession(user);
        }

        @Override
        public void onLoginFail(String error) {
            view.hideProgress();
            view.onLoginFail();
        }
    };
}
