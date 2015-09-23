package hr.foi.challenge.challengeclient.mvp.presenters;

/**
 * Created by igor on 9/23/15.
 */
public interface RegistrationPresenter {

    void register(String username, String mail, String password, String passwordConfirmation,
                  String firstName, String lastName, String skype);
}
