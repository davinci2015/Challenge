package hr.foi.challenge.challengeclient.mvp.presenters;

/**
 * Created by igor on 9/23/15.
 */
public interface FeedbackPresenter {

    void sendFeedback(String feedbackText, String groupName);

    void changeFeedbackType(int type);

    void getGroups();
}
