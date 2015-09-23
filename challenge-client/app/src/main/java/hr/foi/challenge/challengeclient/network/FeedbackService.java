package hr.foi.challenge.challengeclient.network;

import java.util.List;

import hr.foi.challenge.challengeclient.models.Credentials;
import hr.foi.challenge.challengeclient.models.Project;
import hr.foi.challenge.challengeclient.models.User;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

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

    @FormUrlEncoded
    @GET("/challenge/projects.php")
    void fetchProjects(@Field("flag")boolean flag, @Field("mail") String mail, Callback<List<Project>> callback);

}
