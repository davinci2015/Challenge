package hr.foi.challenge.challengeclient.mvp.presenters.impl;

import hr.foi.challenge.challengeclient.mvp.interactors.FeedbackInteractor;
import hr.foi.challenge.challengeclient.mvp.presenters.FeedbackPresenter;
import hr.foi.challenge.challengeclient.mvp.views.FeedbackView;

/**
 * Created by igor on 9/23/15.
 */
public class FeedbackPresenterImpl implements FeedbackPresenter {

    private FeedbackView view;

    private FeedbackInteractor interactor;

    public FeedbackPresenterImpl(FeedbackView view, FeedbackInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void sendFeedback() {

    }
}
