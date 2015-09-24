package hr.foi.challenge.challengeclient.mvp.interactors;

import hr.foi.challenge.challengeclient.mvp.listeners.GroupListener;

/**
 * Created by igor on 9/24/15.
 */
public interface GroupInteractor {

    void getGroups(GroupListener listener);
}
