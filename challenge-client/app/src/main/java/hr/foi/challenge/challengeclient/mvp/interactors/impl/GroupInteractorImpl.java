package hr.foi.challenge.challengeclient.mvp.interactors.impl;

import java.util.List;

import hr.foi.challenge.challengeclient.FeedbackApplication;
import hr.foi.challenge.challengeclient.helpers.Session;
import hr.foi.challenge.challengeclient.mvp.interactors.GroupInteractor;
import hr.foi.challenge.challengeclient.mvp.listeners.GroupListener;
import hr.foi.challenge.challengeclient.network.ApiManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by igor on 9/24/15.
 */
public class GroupInteractorImpl implements GroupInteractor {

    private GroupListener listener;

    @Override
    public void getGroups(GroupListener listener) {
        this.listener = listener;

        long projectID = new Session(FeedbackApplication.getInstance()).retrieveProjectID();

        ApiManager.getService().fetchGroups(projectID, callback);
    }

    private Callback<List<String>> callback = new Callback<List<String>>() {
        @Override
        public void success(List<String> strings, Response response) {
            listener.groupsReceived(strings);
        }

        @Override
        public void failure(RetrofitError error) {
            listener.groupsReceiveFail();
        }
    };
}
