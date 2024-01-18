package com.orbis.demoapp.managers;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RequestManager {
    // Interface for all REST request responses
    public static class RestRequestResponse {
        public JSONObject response;
        public String endpoint;

        RestRequestResponse(String endpoint, JSONObject response) {
            // TODO:
            this.endpoint = endpoint;
            this.response = response;
        }
    }

    public interface RequestHandler {
        void handle(RestRequestResponse response);
    }

    private static final String _TAG = "RequestManager";

    private static RequestManager mInstance;

    private final RequestQueue mVolleyReqQueue;

    public RequestManager(Context context) {
        Log.i(_TAG, "RequestManager initialize");
        mVolleyReqQueue = Volley.newRequestQueue(context);
    }

    public void get(String endpoint, RequestHandler handler) {
        Log.i(_TAG, String.format("Submitting GET request to %s", endpoint));

        // Issue the request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, endpoint, null,
                        response -> handler.handle(new RestRequestResponse(endpoint, response)),
                        error -> {
                    // TODO: Handle error, just log for now
                    Log.e(_TAG, String.format("Request to %s failed", endpoint));
                });

        mVolleyReqQueue.add(jsonObjectRequest);
    }

    public void post(String endpoint, JSONObject jsonObject, RequestHandler handler) {
        Log.i(_TAG, String.format("Submitting POST request to %s", endpoint));

        // Issue the request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, endpoint, jsonObject,
                        response -> handler.handle(new RestRequestResponse(endpoint, response)),
                        error -> {
                            // TODO: Handle error, just log for now
                            Log.e(_TAG, String.format("Request to %s failed", endpoint));
                        });

        mVolleyReqQueue.add(jsonObjectRequest);
    }

    public void destroy() {
        Log.i(_TAG, "RequestManager shutting down");
        mVolleyReqQueue.stop();
    }
}
