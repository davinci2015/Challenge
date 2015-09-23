package hr.foi.challenge.challengeclient.network;

import hr.foi.challenge.challengeclient.models.Credentials;
import hr.foi.challenge.challengeclient.models.User;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.POST;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public interface FeedbackService {

    @POST("/challenge/login.php")
    void userLogin(@Field("obj")Credentials credentials, Callback<String> userCallback);

    @POST("/challenge/register.php")
    void userRegistration(@Field("obj")User user, Callback<User> userCallback);

}
