package hr.foi.challenge.challengeclient.mvp.views;

/**
 * Created by igor on 9/24/15.
 */
public interface ProjectListView extends BaseView {

    void onPostFetchFail();

    void onPostFetchEmpty();

    void onProjectSelected(long id);
}
