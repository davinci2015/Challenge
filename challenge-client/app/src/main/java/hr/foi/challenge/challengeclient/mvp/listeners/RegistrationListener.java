package hr.foi.challenge.challengeclient.mvp.listeners;

import hr.foi.challenge.challengeclient.models.User;

/**
 * Created by igor on 9/23/15.
 */
public interface RegistrationListener {

    void onRegistrationSuccess(User user);

    void onRegistrationFail();
}
