package hr.foi.challenge.challengeclient.mvp.interactors.impl;

import com.google.gson.Gson;

import hr.foi.challenge.challengeclient.FeedbackApplication;
import hr.foi.challenge.challengeclient.helpers.Session;
import hr.foi.challenge.challengeclient.models.NewFeedback;
import hr.foi.challenge.challengeclient.mvp.interactors.FeedbackInteractor;
import hr.foi.challenge.challengeclient.mvp.listeners.FeedbackListener;
import hr.foi.challenge.challengeclient.network.ApiManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by igor on 9/24/15.
 */
public class FeedbackInteractorImpl implements FeedbackInteractor {

    FeedbackListener listener;

    @Override
    public void sendFeedback(String feedbackText, int type, String groupName, FeedbackListener listener) {
        this.listener = listener;

        long personID = new Session(FeedbackApplication.getInstance()).retrieveSession().getId();
        long projectID = new Session(FeedbackApplication.getInstance()).retrieveProjectID();

        String newFeedback = new Gson().toJson(new NewFeedback(feedbackText, type, groupName, personID));

        ApiManager.getService().sendFeedback(projectID, newFeedback, callback);
    }

    Callback<String> callback = new Callback<String>() {
        @Override
        public void success(String s, Response response) {
            listener.onSendSucess();
        }

        @Override
        public void failure(RetrofitError error) {
            listener.onSendFail();
        }
    };
}
