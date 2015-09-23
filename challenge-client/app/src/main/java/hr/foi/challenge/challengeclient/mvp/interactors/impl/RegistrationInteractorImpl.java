package hr.foi.challenge.challengeclient.mvp.interactors.impl;

import com.google.gson.Gson;

import hr.foi.challenge.challengeclient.models.User;
import hr.foi.challenge.challengeclient.mvp.interactors.RegistrationInteractor;
import hr.foi.challenge.challengeclient.mvp.listeners.RegistrationListener;
import hr.foi.challenge.challengeclient.network.ApiManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by igor on 9/23/15.
 */
public class RegistrationInteractorImpl implements RegistrationInteractor {

    private RegistrationListener listener;

    @Override
    public void register(RegistrationListener listener, User user) {
        this.listener = listener;

        ApiManager.getService().userRegistration(new Gson().toJson(user), responseCallback);
    }

    Callback<User> responseCallback = new Callback<User>() {
        @Override
        public void success(User user, Response response) {
            listener.onRegistrationSuccess(user);
        }

        @Override
        public void failure(RetrofitError error) {
            listener.onRegistrationFail();
        }
    };
}
