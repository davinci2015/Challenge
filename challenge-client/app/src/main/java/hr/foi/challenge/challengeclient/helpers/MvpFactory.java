package hr.foi.challenge.challengeclient.helpers;

import hr.foi.challenge.challengeclient.mvp.interactors.impl.FeedbackInteractorImpl;
import hr.foi.challenge.challengeclient.mvp.interactors.impl.GroupInteractorImpl;
import hr.foi.challenge.challengeclient.mvp.interactors.impl.LoginInteractorImpl;
import hr.foi.challenge.challengeclient.mvp.interactors.impl.ProjectInteractorImpl;
import hr.foi.challenge.challengeclient.mvp.interactors.impl.ProjectListInteractorImpl;
import hr.foi.challenge.challengeclient.mvp.interactors.impl.RegistrationInteractorImpl;
import hr.foi.challenge.challengeclient.mvp.presenters.FeedbackPresenter;
import hr.foi.challenge.challengeclient.mvp.presenters.LoginPresenter;
import hr.foi.challenge.challengeclient.mvp.presenters.ProjectListPresenter;
import hr.foi.challenge.challengeclient.mvp.presenters.ProjectPresenter;
import hr.foi.challenge.challengeclient.mvp.presenters.RegistrationPresenter;
import hr.foi.challenge.challengeclient.mvp.presenters.impl.FeedbackPresenterImpl;
import hr.foi.challenge.challengeclient.mvp.presenters.impl.LoginPresenterImpl;
import hr.foi.challenge.challengeclient.mvp.presenters.impl.ProjectListPresenterImpl;
import hr.foi.challenge.challengeclient.mvp.presenters.impl.ProjectPresenterImpl;
import hr.foi.challenge.challengeclient.mvp.presenters.impl.RegistrationPresenterImpl;
import hr.foi.challenge.challengeclient.mvp.views.FeedbackView;
import hr.foi.challenge.challengeclient.mvp.views.LoginView;
import hr.foi.challenge.challengeclient.mvp.views.ProjectListFragmentView;
import hr.foi.challenge.challengeclient.mvp.views.ProjectView;
import hr.foi.challenge.challengeclient.mvp.views.RegistrationView;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public class MvpFactory {

    public static LoginPresenter getPresenter(LoginView view) {
        return new LoginPresenterImpl(view, new LoginInteractorImpl());
    }

    public static RegistrationPresenter getPresenter(RegistrationView view) {
        return new RegistrationPresenterImpl(view, new RegistrationInteractorImpl());
    }

    public static ProjectListPresenter getPresenter(ProjectListFragmentView view) {
        return new ProjectListPresenterImpl(view, new ProjectListInteractorImpl());
    }

    public static ProjectPresenter getPresenter(ProjectView view) {
        return new ProjectPresenterImpl(view, new ProjectInteractorImpl());
    }

    public static FeedbackPresenter getPresenter(FeedbackView view) {
        return new FeedbackPresenterImpl(view, new FeedbackInteractorImpl(), new GroupInteractorImpl());
    }
}
