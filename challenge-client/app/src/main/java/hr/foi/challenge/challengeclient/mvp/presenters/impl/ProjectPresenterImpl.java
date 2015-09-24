package hr.foi.challenge.challengeclient.mvp.presenters.impl;

import java.util.List;

import hr.foi.challenge.challengeclient.models.Feedback;
import hr.foi.challenge.challengeclient.mvp.interactors.ProjectInteractor;
import hr.foi.challenge.challengeclient.mvp.listeners.ProjectListener;
import hr.foi.challenge.challengeclient.mvp.presenters.ProjectPresenter;
import hr.foi.challenge.challengeclient.mvp.views.ProjectView;

/**
 * Created by Tomislav Turek on 24.09.15..
 */
public class ProjectPresenterImpl implements ProjectPresenter {

    ProjectView view;
    ProjectInteractor interactor;

    public ProjectPresenterImpl(ProjectView view, ProjectInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void loadFeedback(int projectId) {
        interactor.fetch(listener, projectId);
    }

    ProjectListener listener = new ProjectListener() {
        @Override
        public void onReceived(List<Feedback> projectList) {
            view.onReceived(projectList);
        }

        @Override
        public void onReceivedEmpty() {
            view.onReceivedEmpty();
        }

        @Override
        public void onReceivedFailed(String error) {
            view.onReceivedFailed();
        }
    };

}
