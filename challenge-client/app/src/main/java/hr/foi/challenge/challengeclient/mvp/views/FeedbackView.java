package hr.foi.challenge.challengeclient.mvp.views;

import java.util.List;

/**
 * Created by igor on 9/23/15.
 */
public interface FeedbackView extends BaseView {

    void onFeedbackSent();

    void onGroupsReceived(List<String> groups);
}
