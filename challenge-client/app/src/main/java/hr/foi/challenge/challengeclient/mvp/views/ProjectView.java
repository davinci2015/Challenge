package hr.foi.challenge.challengeclient.mvp.views;

import java.util.List;

import hr.foi.challenge.challengeclient.models.Feedback;

/**
 * Created by Tomislav Turek on 24.09.15..
 */
public interface ProjectView extends BaseView {

    void onReceived(List<Feedback> projectList);
    void onReceivedFailed();
    void onReceivedEmpty();

}
