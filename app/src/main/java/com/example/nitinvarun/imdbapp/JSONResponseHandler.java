package com.example.nitinvarun.imdbapp;

import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NitinVarun on 6/4/2015.
 */
public class JSONResponseHandler implements ResponseHandler<List<String>> {
    private static final String movieName_tag = "Title";
    private static final String posterURL_tag = "Poster";
    private static final String directorName_tag = "Director";
    private static final String rating_tag = "imdbRating";
    private static final String response = "Response";

    @Override
    public List<String> handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        List<String> result =  new ArrayList<String>();
        String JSONResponse = new BasicResponseHandler().handleResponse(httpResponse);
        Log.d("Debuger","INSIDE HANDLER");
        try {
            JSONObject responseObject = (JSONObject) new JSONTokener(JSONResponse).nextValue();
            if(responseObject.getString(response).compareTo("False") == 0){
                return null;
            }
            result.add(responseObject.getString(movieName_tag));
            result.add(responseObject.getString(posterURL_tag));
            result.add(responseObject.getString(directorName_tag));
            result.add(responseObject.getString(rating_tag));
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
