package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech4life.adapter.PostsAdapter;
import com.example.tech4life.recycleritems.Notifications;
import com.example.tech4life.recycleritems.Post;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    
    DrawerLayout drawerLayout;
    ArrayList<Post> mPost = new ArrayList<Post>();
    RecyclerView mRecyclerPost;
    PostsAdapter mPostAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //setContentView(R.layout.report_screen);
        //setContentView(R.layout.login_activity_screen);
        //setContentView(R.layout.register_activity_screen);
        //setContentView(R.layout.post_activity_screen);

        //setContentView(R.layout.user_activity_screen);

        //setContentView(R.layout.user_activity_screen);

        //Assign variable
        mPostAdapter = new PostsAdapter(this, mPost);
        drawerLayout = findViewById(R.id.drawer_layout);
        mRecyclerPost = findViewById(R.id.post_recyclerView);
        mRecyclerPost.setAdapter(mPostAdapter);
        mRecyclerPost.setLayoutManager(new LinearLayoutManager(this));
        fetchPostsFromAPI();

        //recycler post item
    }

    private void fetchPostsFromAPI() {
        //Log.d("API", "cccccccccccccccccccccccccccccccccc");
        String URL = "http://10.0.2.2:8000/api/post";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JsonFactory jsonFactory = new JsonFactory();
                        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);

                        try {
                            JsonNode arrayNode = objectMapper.readTree(response);

                            for (JsonNode jsonNode : arrayNode) {
                                mPost.add(new Post(
                                        jsonNode.get("display_name").asText(),
                                        jsonNode.get("avatar").asText(),
                                        jsonNode.get("created_at").asText(),
                                        jsonNode.get("image_title").asText(),
                                        jsonNode.get("title").asText()
                                ));
                            }
                            mPostAdapter.notifyDataSetChanged();

                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                 }
        });
        queue.add(stringRequest);

        /*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        String strDate;
        try {
            date = formatter.parse("12/12/2020");
            strDate = formatter.format(date);
        } catch (ParseException e) {
            return;
        }*/
    }
    public void ClickMenu(View view){
        //Open Drawer
        openDrawer(drawerLayout);
    }
    public static void openDrawer(DrawerLayout drawerLayout){
        //Open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        //Close drawer
        closeDrawer(drawerLayout);
    }
    public static void closeDrawer(DrawerLayout drawerLayout){
        //Close drawer layout
        //Check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            //When drawer is open
            //Close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickHome(View view){
        //recreate activity
        recreate();
    }
    public void ClickPost(View view){
        //redirect activity to post
        redirecActivity(this, SerieActivity.Post_Detail.class);
    }
    public void ClickSeries(View view){
        //redirect activity to series
        redirecActivity(this, SerieActivity.class);
    }
//    public void ClickCategory(View view){
//        //redirect activity to category
//        redirecActivity(this,);
//    }
    public void ClickNotification(View view){
        //redirect activity to noitification
        redirecActivity(this, NotificationActivity.class);
    }
    public void ClickAnnouncement(View view){
        //redirect activity to announcement
        redirecActivity(this, AnnouncementActivity.class);
    }
//    public void ClicksSetting(View view){
//        //redirect activity to setting
//        redirecActivity(this,);
//    }
    public void ClickLogout(View view){
        //close app
        logout(this);
    }
    public static void logout(final Activity activity){
        //redirect activity to setting
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //Set title
        builder.setTitle("Logout");
        //Set mess
        builder.setMessage("Are you sure you want to logout?");
        //Positive yes button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                activity.finishAffinity();
                //Exit app
                System.exit(0);
            }
        });
        //Negative no button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               //Dissmiss dialog
               dialog.dismiss();
            }
        });
        //Show dialog
        builder.show();
    }

    public static void redirecActivity(Activity activity, Class aClass) {
        //Initialize intent
        Intent intent = new Intent(activity,aClass);
        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        closeDrawer(drawerLayout);
    }
}