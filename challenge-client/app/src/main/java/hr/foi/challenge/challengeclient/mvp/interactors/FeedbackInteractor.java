package hr.foi.challenge.challengeclient.mvp.interactors;

import hr.foi.challenge.challengeclient.mvp.listeners.FeedbackListener;

/**
 * Created by igor on 9/24/15.
 */
public interface FeedbackInteractor {

    void sendFeedback(String feedbackText, int type, String groupName, FeedbackListener listener);
}
