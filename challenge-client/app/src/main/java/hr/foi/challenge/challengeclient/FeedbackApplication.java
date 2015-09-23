package hr.foi.challenge.challengeclient;

import android.app.Application;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public class FeedbackApplication extends Application {

    private static FeedbackApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static FeedbackApplication getInstance() { return instance; }
}
