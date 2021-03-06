package hr.foi.challenge.challengeclient.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import hr.foi.challenge.challengeclient.activities.LoginActivity;
import hr.foi.challenge.challengeclient.models.User;

/**
 * Session class for login
 * Created by Tomo on 20.7.2015..
 */
public class Session {
    SharedPreferences preferences;
    Editor editor;
    Context context;

    /**
     * Session is created from application context
     * @param context current application context
     */
    public Session(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("ChallengeSession", 0);
        editor = preferences.edit();
        editor.apply();
    }

    /**
     * Creates session object by putting current employee to shared preferences
     * @param user current employee logged into the app
     */
    public void createSession(User user) {
        editor.putString("user", new Gson().toJson(user));
        editor.commit();
    }

    /**
     * Returns current employee stored in session
     * @return current employee logged into the app
     */
    public User retrieveSession() {
        return new Gson().fromJson(preferences.getString("user", null), User.class);
    }

    public void saveProjectID(long id) {
        editor.putLong("project_id", id);
        editor.commit();
    }

    public void saveProjectTitle(String title) {
        editor.putString("project_title", title);
        editor.commit();
    }

    public String retrieveProjectTitle() {
        return preferences.getString("project_title", null);
    }

    public long retrieveProjectID() {
        return preferences.getLong("project_id", 0);
    }

    public void saveProjectDescription(String desc) {
        editor.putString("project_desc", desc);
        editor.commit();
    }

    public String retrieveProjectDescription() {
        return preferences.getString("project_desc", null);
    }

    /**
     * Destroys current session (logs out employee)
     */
    public void destroySession() {
        editor.clear();
        editor.commit();

        Intent toLogin = new Intent(this.context, LoginActivity.class);
        toLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        toLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        this.context.startActivity(toLogin);
        ((Activity) context).finish();
    }

    @Override
    public String toString() {
        return this.preferences.toString();
    }
}
