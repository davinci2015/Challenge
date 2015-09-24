package hr.foi.challenge.challengeclient.mvp.presenters.impl;

import java.util.List;

import hr.foi.challenge.challengeclient.R;
import hr.foi.challenge.challengeclient.mvp.interactors.FeedbackInteractor;
import hr.foi.challenge.challengeclient.mvp.interactors.GroupInteractor;
import hr.foi.challenge.challengeclient.mvp.listeners.FeedbackListener;
import hr.foi.challenge.challengeclient.mvp.listeners.GroupListener;
import hr.foi.challenge.challengeclient.mvp.presenters.FeedbackPresenter;
import hr.foi.challenge.challengeclient.mvp.views.FeedbackView;

/**
 * Created by igor on 9/23/15.
 */
public class FeedbackPresenterImpl implements FeedbackPresenter {

    private FeedbackView view;

    private FeedbackInteractor feedbackInteractor;

    private GroupInteractor groupInteractor;

    private int feedbackType;

    public FeedbackPresenterImpl(FeedbackView view, FeedbackInteractor feedbackInteractor, GroupInteractor groupInteractor) {
        this.view = view;
        this.feedbackInteractor = feedbackInteractor;
        this.groupInteractor = groupInteractor;
        feedbackType = 0;
    }

    @Override
    public void sendFeedback(String feedbackText) {
        view.showProgress();
        feedbackInteractor.sendFeedback(feedbackText, feedbackType, null, feedbackListener);
    }

    @Override
    public void changeFeedbackType(int type) {
        feedbackType = type;
    }

    @Override
    public void getGroups() {
        view.showProgress();
        groupInteractor.getGroups(groupListener);
    }

    private GroupListener groupListener = new GroupListener() {
        @Override
        public void groupsReceived(List<String> groups) {
            view.hideProgress();
            view.onGroupsReceived(groups);
        }

        @Override
        public void groupsReceiveFail() {
            view.hideProgress();
            view.showError(R.string.groups_error);
        }
    };

    private FeedbackListener feedbackListener = new FeedbackListener() {
        @Override
        public void onSendSucess() {
            view.hideProgress();
            view.showError(R.string.feedback_sent);
        }

        @Override
        public void onSendFail() {
            view.hideProgress();
            view.showError(R.string.something_wrong);
        }
    };
}
