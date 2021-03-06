package hr.foi.challenge.challengeclient.mvp.presenters.impl;

import java.util.List;

import hr.foi.challenge.challengeclient.models.Project;
import hr.foi.challenge.challengeclient.mvp.interactors.ProjectListInteractor;
import hr.foi.challenge.challengeclient.mvp.listeners.ProjectListListener;
import hr.foi.challenge.challengeclient.mvp.presenters.ProjectListPresenter;
import hr.foi.challenge.challengeclient.mvp.views.ProjectListFragmentView;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public class ProjectListPresenterImpl implements ProjectListPresenter {

    ProjectListFragmentView view;
    ProjectListInteractor interactor;

    public ProjectListPresenterImpl(ProjectListFragmentView view, ProjectListInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void loadProjects(boolean flag) {
        view.showProgress();
        interactor.fetch(listener, flag);
    }

    @Override
    public void sendCode(String code) {
        view.showProgress();
        interactor.send(listener, code);
    }

    private ProjectListListener listener = new ProjectListListener() {
        @Override
        public void onReceived(List<Project> projects) {
            view.onReceived(projects);
        }

        @Override
        public void onReceivedEmpty() {
            view.onReceivedEmpty();
        }

        @Override
        public void onReceivedFailed(String error) {
            view.onReceivedFailed();
        }

        @Override
        public void onCodeSuccess() {
            view.onCodeSuccess();
        }

        @Override
        public void onCodeFailed() {
            view.onCodeFailed();
        }

        @Override
        public void onCodeUsed() {
            view.onCodeUsed();
        }
    };

}
