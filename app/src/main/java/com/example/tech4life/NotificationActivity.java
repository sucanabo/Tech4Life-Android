package com.example.tech4life;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech4life.adapter.NotificationsAdapter;
import com.example.tech4life.recycleritems.Notifications;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    private ArrayList<Notifications> mNotifications;
    private RecyclerView mRecyclerNotifications;
    private NotificationsAdapter mNotificationsAdapter;


    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mRecyclerNotifications = findViewById(R.id.notificationRecyclerView);
        mNotifications = new ArrayList<>();

        mNotificationsAdapter = new NotificationsAdapter(this, mNotifications);
        mRecyclerNotifications.setAdapter(mNotificationsAdapter);
        mRecyclerNotifications.setLayoutManager(new LinearLayoutManager(this));
        drawerLayout = findViewById(R.id.drawer_layout);

        createNotificationsList();
    }
    public void ClickMenu(View view){
        MainActivity.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){
        MainActivity.closeDrawer(drawerLayout);
    }
    public void ClickPost(View view){
        MainActivity.redirecActivity(this,MainActivity.class);
    }
    public void ClickSeries(View view){
        recreate();
    }
    public void ClickNotification(View view){
        MainActivity.redirecActivity(this,NotificationActivity.class);
    }
    public void ClickAnnouncement(View view){
        MainActivity.redirecActivity(this,AnnouncementActivity.class);
    }
    public void ClickLogout(View view){
        //close app
        MainActivity.logout(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        MainActivity.closeDrawer(drawerLayout);
    }
     private void createNotificationsList() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8000/api/notifications";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response", response);

                        JsonFactory jsonFactory = new JsonFactory();
                        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);

                        try {
                            JsonNode arrayNode = objectMapper.readTree(response);

                            for (JsonNode jsonNode : arrayNode) {
                                mNotifications.add(new Notifications(jsonNode.get("content").asText(), "123", R.drawable.ic_notification));
                            }
                            mNotificationsAdapter.notifyDataSetChanged();

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
    }
}