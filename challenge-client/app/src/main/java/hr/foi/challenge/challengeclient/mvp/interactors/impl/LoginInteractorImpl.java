package hr.foi.challenge.challengeclient.mvp.interactors.impl;

import android.util.Log;

import com.google.gson.Gson;

import hr.foi.challenge.challengeclient.models.Credentials;
import hr.foi.challenge.challengeclient.models.User;
import hr.foi.challenge.challengeclient.mvp.interactors.LoginInteractor;
import hr.foi.challenge.challengeclient.mvp.listeners.LoginListener;
import hr.foi.challenge.challengeclient.network.ApiManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public class LoginInteractorImpl implements LoginInteractor {

    private LoginListener listener;

    @Override
    public void login(LoginListener listener, String username, String password) {
        this.listener = listener;
        Credentials credentials = new Credentials(username, password);
        ApiManager.getService().userLogin(new Gson().toJson(credentials), responseCallback);
    }

    private Callback<String> responseCallback = new Callback<String>() {

        @Override
        public void success(String user, Response response) {
            Log.d("hr.foi.challenge", user);
            //listener.onLoginSuccess(user);
        }

        @Override
        public void failure(RetrofitError error) {
            listener.onLoginFail(error.getMessage());
        }
    };

}
