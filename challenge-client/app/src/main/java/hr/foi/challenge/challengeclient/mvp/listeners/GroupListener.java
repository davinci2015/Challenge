package hr.foi.challenge.challengeclient.mvp.listeners;

import java.util.List;

import hr.foi.challenge.challengeclient.models.Group;

/**
 * Created by igor on 9/24/15.
 */
public interface GroupListener {

    void groupsReceived(List<Group> groups);

    void groupsReceiveFail();
}
