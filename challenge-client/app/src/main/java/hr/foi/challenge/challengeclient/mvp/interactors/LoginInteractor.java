package hr.foi.challenge.challengeclient.mvp.interactors;

import hr.foi.challenge.challengeclient.mvp.listeners.LoginListener;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public interface LoginInteractor {

    void login(LoginListener listener, String username, String password);

}
