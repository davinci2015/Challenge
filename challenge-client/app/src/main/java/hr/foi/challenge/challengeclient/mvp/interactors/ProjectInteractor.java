package hr.foi.challenge.challengeclient.mvp.interactors;

import hr.foi.challenge.challengeclient.mvp.listeners.ProjectListener;

/**
 * Created by Tomislav Turek on 24.09.15..
 */
public interface ProjectInteractor {

    void fetch(ProjectListener listener, int projectId);

}
