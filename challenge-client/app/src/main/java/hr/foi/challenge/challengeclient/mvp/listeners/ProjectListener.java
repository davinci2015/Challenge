package hr.foi.challenge.challengeclient.mvp.listeners;

import java.util.List;

import hr.foi.challenge.challengeclient.models.Feedback;

/**
 * Created by Tomislav Turek on 24.09.15..
 */
public interface ProjectListener {

    void onReceived(List<Feedback> projectList);
    void onReceivedEmpty();
    void onReceivedFailed(String error);

}
