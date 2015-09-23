package hr.foi.challenge.challengeclient.mvp.interactors.impl;

import android.util.Log;

import java.util.List;

import hr.foi.challenge.challengeclient.FeedbackApplication;
import hr.foi.challenge.challengeclient.helpers.Session;
import hr.foi.challenge.challengeclient.models.Project;
import hr.foi.challenge.challengeclient.mvp.interactors.ProjectListInteractor;
import hr.foi.challenge.challengeclient.mvp.listeners.ProjectListListener;
import hr.foi.challenge.challengeclient.network.ApiManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public class ProjectListInteractorImpl implements ProjectListInteractor {

    ProjectListListener listener;

    @Override
    public void fetch(ProjectListListener listener, boolean flag) {
        this.listener = listener;
        String mail = null;
        if(!flag) {
            mail = new Session(FeedbackApplication.getInstance()).retrieveSession().getMail();
        }
        ApiManager.getService().fetchProjects(mail, projectFetch);
    }

    private Callback<List<Project>> projectFetch = new Callback<List<Project>>() {
        @Override
        public void success(List<Project> projects, Response response) {
            listener.onReceived(projects);
        }

        @Override
        public void failure(RetrofitError error) {
            listener.onReceivedFailed(error.getMessage());
        }
    };
}
