package hr.foi.challenge.challengeclient.mvp.listeners;

import java.util.List;

import hr.foi.challenge.challengeclient.models.Project;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public interface ProjectListListener {

    void onReceived(List<Project> projectList);
    void onReceivedEmpty();
    void onReceivedFailed(String error);

    void onCodeSuccess();
    void onCodeFailed();
    void onCodeUsed();

}
