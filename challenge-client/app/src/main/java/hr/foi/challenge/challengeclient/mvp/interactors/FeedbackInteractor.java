package hr.foi.challenge.challengeclient.mvp.interactors;

import hr.foi.challenge.challengeclient.models.Multimedia;
import hr.foi.challenge.challengeclient.models.NewFeedback;

/**
 * Created by igor on 9/24/15.
 */
public interface FeedbackInteractor {

    void sendFeedback(NewFeedback feedback, Multimedia multimedia);
}
