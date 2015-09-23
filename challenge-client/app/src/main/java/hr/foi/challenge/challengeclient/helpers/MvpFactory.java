package hr.foi.challenge.challengeclient.helpers;

import hr.foi.challenge.challengeclient.mvp.interactors.impl.LoginInteractorImpl;
import hr.foi.challenge.challengeclient.mvp.presenters.LoginPresenter;
import hr.foi.challenge.challengeclient.mvp.presenters.impl.LoginPresenterImpl;
import hr.foi.challenge.challengeclient.mvp.views.LoginView;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public class MvpFactory {

    public static LoginPresenter getPresenter(LoginView view) {
        return new LoginPresenterImpl(view, new LoginInteractorImpl());
    }

}
