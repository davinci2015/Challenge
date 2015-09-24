package hr.foi.challenge.challengeclient.network;

import java.util.List;

import hr.foi.challenge.challengeclient.models.Credentials;
import hr.foi.challenge.challengeclient.models.Feedback;
import hr.foi.challenge.challengeclient.models.Project;
import hr.foi.challenge.challengeclient.models.User;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public interface FeedbackService {

    @FormUrlEncoded
    @POST("/challenge/login.php")
    void userLogin(@Field("obj")String credentials, Callback<User> userCallback);

    @FormUrlEncoded
    @POST("/challenge/register.php")
    void userRegistration(@Field("obj")String user, Callback<User> userCallback);

    @GET("/challenge/get_projects_data.php")
    void fetchProjects(@Query("mail") String mail, Callback<List<Project>> callback);

    @GET("/challenge/get_feedback.php")
    void fetchFeedbacks(@Query("project_id") long id, Callback<List<Feedback>> callback);

    @POST("/challenge/invintation.php")
    void sendInviteCode(@Field("code") String code, Callback callback);

}
