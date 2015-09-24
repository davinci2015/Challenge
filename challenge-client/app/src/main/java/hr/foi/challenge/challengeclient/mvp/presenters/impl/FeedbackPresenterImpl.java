package hr.foi.challenge.challengeclient.mvp.presenters.impl;

import hr.foi.challenge.challengeclient.mvp.interactors.FeedbackInteractor;
import hr.foi.challenge.challengeclient.mvp.presenters.FeedbackPresenter;
import hr.foi.challenge.challengeclient.mvp.views.FeedbackView;

/**
 * Created by igor on 9/23/15.
 */
public class FeedbackPresenterImpl implements FeedbackPresenter {

    private FeedbackView view;

    private FeedbackInteractor feedbackInteractor;

    private int feedbackType;

    public FeedbackPresenterImpl(FeedbackView view, FeedbackInteractor feedbackInteractor) {
        this.view = view;
        this.feedbackInteractor = feedbackInteractor;
        feedbackType = 0;
    }

    @Override
    public void sendFeedback(String feedbackText) {

    }

    @Override
    public void changeFeedbackType(int type) {
        feedbackType = type;
    }

    @Override
    public void getGroups() {
        
    }
}
