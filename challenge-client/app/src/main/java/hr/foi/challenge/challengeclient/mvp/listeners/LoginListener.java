package hr.foi.challenge.challengeclient.mvp.listeners;

import hr.foi.challenge.challengeclient.models.User;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public interface LoginListener {

    void onLoginSuccess(User user);
    void onLoginFail(String error);

}
