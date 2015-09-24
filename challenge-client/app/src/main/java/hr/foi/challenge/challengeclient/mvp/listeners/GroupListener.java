package hr.foi.challenge.challengeclient.mvp.listeners;

import java.util.List;

/**
 * Created by igor on 9/24/15.
 */
public interface GroupListener {

    void groupsReceived(List<String> groups);

    void groupsReceiveFail();
}
