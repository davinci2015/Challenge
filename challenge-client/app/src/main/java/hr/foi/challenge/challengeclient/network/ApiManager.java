package hr.foi.challenge.challengeclient.network;

import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.Date;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Tomislav Turek on 23.09.15..
 */
public class ApiManager {

    public static final String ACCEPT = "Accept";
    public static final String ACCEPT_HEADER = "application/json";
    public static final String AUTHORIZATION = "Authorization";


    private static final String TAG = "Network";

    private static final String API_ENDPOINT = "https://boatit.infinum.co";

    private static final OkClient OK_CLIENT = new OkClient(new OkHttpClient());

    private static final Gson GSON = new GsonBuilder()
            .create();

    private static final GsonConverter GSON_CONVERTER = new GsonConverter(GSON);

    private static final RestAdapter.Log LOG = new RestAdapter.Log() {
        @Override
        public void log(String message) {
            Log.d(TAG, message);
        }
    };

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setConverter(GSON_CONVERTER)
            .setEndpoint(API_ENDPOINT)
            .setClient(OK_CLIENT)
            .setLog(LOG)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

    private static final FeedbackService SERVICE = REST_ADAPTER.create(FeedbackService.class);

    public static FeedbackService getService() {
        return SERVICE;
    }

}
