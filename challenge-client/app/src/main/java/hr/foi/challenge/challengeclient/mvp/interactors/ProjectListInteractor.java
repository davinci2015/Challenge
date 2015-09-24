package hr.foi.challenge.challengeclient.mvp.interactors;

import hr.foi.challenge.challengeclient.mvp.listeners.ProjectListListener;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public interface ProjectListInteractor {

    void fetch(ProjectListListener listener, boolean flag);
    void send(ProjectListListener listener, String code);

}
