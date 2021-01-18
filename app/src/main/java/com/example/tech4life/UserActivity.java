package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech4life.singleton.AccountSession;

public class UserActivity extends AppCompatActivity {

    private TextView totalPost;
    private TextView totalView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        totalPost = findViewById(R.id.txt_toatal_post);
        totalView = findViewById(R.id.txt_toatal_view);
        showStatisticPost();
        showStatisticView();
    }

    public void lauchClipPostActivity(View view) {
        Intent intent = new Intent(this, ClipPostActivity.class);
        startActivity(intent);
    }

    public void showStatisticPost() {
        String url = "http://10.0.2.2:8000/api/statisticpost/" + AccountSession.getAccount().getId();

        RequestQueue queue = Volley.newRequestQueue(UserActivity.this);
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response nha", response);
                        totalPost.setText(response.substring(9,10));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorClip", error.toString());
                    }
                }
        );
        queue.add(postRequest);
    }

    public void showStatisticView() {
        String url = "http://10.0.2.2:8000/api/statisticview/" + AccountSession.getAccount().getId();

        RequestQueue queue = Volley.newRequestQueue(UserActivity.this);
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response nha", response);
                        totalView.setText(response.substring(10,13));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorClip", error.toString());
                    }
                }
        );
        queue.add(postRequest);
    }
}