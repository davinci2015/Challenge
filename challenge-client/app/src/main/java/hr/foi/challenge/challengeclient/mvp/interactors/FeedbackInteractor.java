package hr.foi.challenge.challengeclient.mvp.interactors;

import hr.foi.challenge.challengeclient.models.Multimedia;

/**
 * Created by igor on 9/24/15.
 */
public interface FeedbackInteractor {

    void sendFeedback(long projectID, long personID, Multimedia multimedia);
}
