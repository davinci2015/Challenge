package hr.foi.challenge.challengeclient.mvp.views;

import java.util.List;

import hr.foi.challenge.challengeclient.models.Project;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public interface ProjectListView {

    void onReceived(List<Project> projectList);
    void onReceivedFailed();
    void onReceivedEmpty();

    void onCodeSuccess();
    void onCodeFailed();

}
