package hr.foi.challenge.challengeclient.helpers;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Class that makes calls to RESTful service
 * Created by Tomislav Turek on 13.7.2015..
 */
public class ServiceCaller {

    static HttpURLConnection connection;

    /**
     * Call method to service without parameters
     * @param uri URL location to call
     * @param method request method used in service call
     * @return service JSON response in String
     * @throws IOException
     */
    @NonNull
    public static String call(URL uri, String method) throws IOException {
        return call(uri, method, null);
    }

    /**
     * Call method to service with parameters
     * @param url URL location to call
     * @param method request method used in service call
     * @param params data to send to service
     * @return service JSON response in String
     * @throws IOException
     */
    @NonNull
    public static String call(URL url, String method, String params) throws IOException {
        System.setProperty("jsse.enableSNIExtension", "false");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("project_id", params);
        connection.setRequestMethod(method);
        connection.setDoInput(true);

        if (params != null) {
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(new Gson().toJson(params));
            writer.flush();
            writer.close();
            os.close();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            response.append(line);
        }
        return response.toString();
    }

}
