package com.example.tech4life.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpRequest {

    private String URL = "";
    private int method;
    private Context context;
    private JsonNode data;
    public HttpRequest(String URL, int method, Context context) {
        this.URL = URL;
        this.method = method;
        this.context = context;
    }
    public JsonNode call()  {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(this.method, this.URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonFactory jsonFactory = new JsonFactory();
                        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
                        try {
                            data = objectMapper.readTree(response);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
        return data;
    }
    public JsonNode getData() {
        return call();
    }
}
