package hr.foi.challenge.challengeclient.mvp.interactors.impl;

import android.util.Log;

import java.util.List;

import hr.foi.challenge.challengeclient.FeedbackApplication;
import hr.foi.challenge.challengeclient.helpers.Session;
import hr.foi.challenge.challengeclient.models.Group;
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

    //private GroupListener listener;

    @Override
    public void getGroups(final GroupListener listener) {

        long projectID = new Session(FeedbackApplication.getInstance()).retrieveProjectID();

        Log.e("test.test", "PUSH");
        ApiManager.getService().fetchGroups(projectID, new Callback<List<Group>>() {
            @Override
            public void success(List<Group> groups, Response response) {
                listener.groupsReceived(groups);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.groupsReceiveFail();
            }
        });

        Log.e("test.test", "PULL");
    }
/*
    private Callback<List<Group>> callback = new Callback<List<Group>>() {
        @Override
        public void success(List<Group> strings, Response response) {
            Log.e("test.test", "UŠO U SUKSES");
            listener.groupsReceived(strings);
        }

        @Override
        public void failure(RetrofitError error) {
            Log.e("test.test", "UŠO U FEJL");
            listener.groupsReceiveFail();
        }
    };
*/
}
