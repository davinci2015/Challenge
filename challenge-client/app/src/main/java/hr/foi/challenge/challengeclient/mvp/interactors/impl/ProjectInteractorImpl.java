package hr.foi.challenge.challengeclient.mvp.interactors.impl;

import android.util.Log;

import java.util.List;

import hr.foi.challenge.challengeclient.models.Feedback;
import hr.foi.challenge.challengeclient.mvp.interactors.ProjectInteractor;
import hr.foi.challenge.challengeclient.mvp.listeners.ProjectListener;
import hr.foi.challenge.challengeclient.network.ApiManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Tomislav Turek on 24.09.15..
 */
public class ProjectInteractorImpl implements ProjectInteractor {

    ProjectListener listener;

    @Override
    public void fetch(ProjectListener listener, long projectId) {
        this.listener = listener;
        ApiManager.getService().fetchFeedbacks(projectId, feedbackFetch);
    }

    private Callback<List<Feedback>> feedbackFetch = new Callback<List<Feedback>>() {
        @Override
        public void success(List<Feedback> feedbacks, Response response) {
            for(Feedback f: feedbacks) {
                Log.e("test.test", f.getText() + " " + f.getPerson());
            }
            listener.onReceived(feedbacks);
        }

        @Override
        public void failure(RetrofitError error) {
            listener.onReceivedFailed(error.getMessage());
        }
    };
}
