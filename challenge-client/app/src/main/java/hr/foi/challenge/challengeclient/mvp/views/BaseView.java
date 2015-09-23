package hr.foi.challenge.challengeclient.mvp.views;

import android.support.annotation.StringRes;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public interface BaseView {

    void showProgress();

    void hideProgress();

    void showError(@StringRes int error);

}
