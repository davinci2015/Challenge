package hr.foi.challenge.challengeclient.mvp.presenters.impl;

import android.text.TextUtils;

import hr.foi.challenge.challengeclient.FeedbackApplication;
import hr.foi.challenge.challengeclient.R;
import hr.foi.challenge.challengeclient.helpers.Session;
import hr.foi.challenge.challengeclient.models.Credentials;
import hr.foi.challenge.challengeclient.models.User;
import hr.foi.challenge.challengeclient.mvp.interactors.RegistrationInteractor;
import hr.foi.challenge.challengeclient.mvp.listeners.RegistrationListener;
import hr.foi.challenge.challengeclient.mvp.presenters.RegistrationPresenter;
import hr.foi.challenge.challengeclient.mvp.views.RegistrationView;

/**
 * Created by igor on 9/23/15.
 */
public class RegistrationPresenterImpl implements RegistrationPresenter {

    private static final int PASSWORD_LENGTH = 6;

    private static final int USERNAME_LENGTH = 5;

    private static final String SPACE = " ";

    private static final String MAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

    private RegistrationView view;

    private RegistrationInteractor interactor;

    public RegistrationPresenterImpl(RegistrationView view, RegistrationInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void register(String username, String mail, String password, String passwordConfirmation,
                         String firstName, String lastName, String skype) {

        if (isDataValid(username, mail, password, passwordConfirmation, firstName, lastName)) {
            User user = new User(new Credentials(username, password), firstName, lastName, mail, skype);

            view.showProgress();
            interactor.register(listener, user);
        }
    }

    private RegistrationListener listener = new RegistrationListener() {
        @Override
        public void onRegistrationSuccess(User user) {
            view.hideProgress();
            view.onRegistrationSuccess();
            new Session(FeedbackApplication.getInstance()).createSession(user);
        }

        @Override
        public void onRegistrationFail() {
            view.hideProgress();
            view.onRegistrationFail();
        }
    };

    private boolean isDataValid(String username, String mail, String password,
                                String passwordConfirmation, String firstName, String lastName) {
        boolean ret = false;

        if (!isFormComplete(username, mail, password, passwordConfirmation, firstName, lastName)) {
            view.showError(R.string.registration_fields_error);
        } else if (!isUsernameLengthValid(username)) {
            view.showError(R.string.registration_username_length_error);
        } else if (!isUsernameValid(username)) {
            view.showError(R.string.registration_username_error);
        } else if (!isMailValid(mail)) {
            view.showError(R.string.registration_email_error);
        } else if (!isPasswordValid(password)) {
            view.showError(R.string.registration_password_length_error);
        } else if (!isConfirmationValid(password, passwordConfirmation)) {
            view.showError(R.string.registration_password_confirmation_error);
        } else {
            ret = true;
        }

        return ret;
    }

    private boolean isFormComplete(String username, String mail, String password,
                                   String passwordConfirmation, String firstName, String lastName) {

        return !(TextUtils.isEmpty(username) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(passwordConfirmation) || TextUtils.isEmpty(firstName)
                || TextUtils.isEmpty(lastName));
    }

    private boolean isUsernameLengthValid(String username) {
        return username.length() >= USERNAME_LENGTH;
    }

    private boolean isUsernameValid(String username) {
        return !username.contains(SPACE);
    }

    private boolean isMailValid(String mail) {
        return mail.matches(MAIL_REGEX);
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= PASSWORD_LENGTH;
    }

    private boolean isConfirmationValid(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }
}
