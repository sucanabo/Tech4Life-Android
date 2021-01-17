package com.example.tech4life;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech4life.adapter.PostsAdapter;
import com.example.tech4life.recycleritems.Announcements;
import com.example.tech4life.recycleritems.Post;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class PostFragment extends Fragment {

    RecyclerView mRecyclerPost;
    PostsAdapter mPostAdapter;
    ArrayList<Post> mPost = new ArrayList<Post>();


    public PostFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Assign variable
        View view = inflater.inflate(R.layout.fragment_post,container,false);


        mPostAdapter = new PostsAdapter(getContext(),mPost);
        mRecyclerPost = view.findViewById(R.id.post_recyclerView);
        mRecyclerPost.setHasFixedSize(true);
        mRecyclerPost.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerPost.setAdapter(mPostAdapter);
        mRecyclerPost.setAdapter(mPostAdapter);

        fetchPostsFromAPI();

        // Inflate the layout for this fragment
        return view;
    }

    private void fetchPostsFromAPI() {
        //Log.d("API", "cccccccccccccccccccccccccccccccccc");
        String URL = "http://10.0.2.2:8000/api/post";
        RequestQueue queue = Volley.newRequestQueue(getContext());
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
                                        jsonNode.get("id").asText()
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
}