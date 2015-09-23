package hr.foi.challenge.challengeclient.mvp.interactors;

import hr.foi.challenge.challengeclient.models.User;
import hr.foi.challenge.challengeclient.mvp.listeners.RegistrationListener;

/**
 * Created by igor on 9/23/15.
 */
public interface RegistrationInteractor {

    void register(RegistrationListener listener, User user);
}
