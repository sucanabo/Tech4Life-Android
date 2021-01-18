package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech4life.adapter.ClipPostAdapter;
import com.example.tech4life.adapter.PostsAdapter;
import com.example.tech4life.recycleritems.ClipPost;
import com.example.tech4life.recycleritems.Post;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class ClipPostActivity extends AppCompatActivity {

    RecyclerView mRecyclerPost;
    PostsAdapter mPostAdapter;
    ArrayList<Post> mPost = new ArrayList<Post>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_post);

        mPostAdapter = new PostsAdapter(this,mPost);
        mRecyclerPost = findViewById(R.id.clip_post_recyclerview);
        mRecyclerPost.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerPost.setAdapter(mPostAdapter);
        fetchPostsFromAPI();
    }

    private void fetchPostsFromAPI() {
        //Log.d("API", "cccccccccccccccccccccccccccccccccc");
        String URL = "http://10.0.2.2:8000/api/getClipPost";
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
                                        jsonNode.get("title").asText(),
                                        jsonNode.get("content").asText(),
                                        jsonNode.get("view").asText(),
                                        jsonNode.get("vote").asText(),
                                        jsonNode.get("comment").asText(),
                                        jsonNode.get("clipped").asText(),
                                        jsonNode.get("username").asText(),
                                        jsonNode.get("id").asText(),
                                        jsonNode.get("user_id").asText()

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
    }
}