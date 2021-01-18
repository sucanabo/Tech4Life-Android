package com.example.tech4life;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech4life.adapter.AnnouncementsAdapter;
import com.example.tech4life.adapter.CategoryAdapter;
import com.example.tech4life.recycleritems.Announcements;
import com.example.tech4life.recycleritems.Category;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class CategoryFragment  extends Fragment {
    RecyclerView mRecyclerCategory;
    CategoryAdapter mAdapter;
    ArrayList<Category> mCategory = new ArrayList<Category>();
    public CategoryFragment() {

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


        mAdapter = new CategoryAdapter(getContext(),mCategory);
        mRecyclerCategory = view.findViewById(R.id.post_recyclerView);
        mRecyclerCategory.setHasFixedSize(true);
        mRecyclerCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerCategory.setAdapter(mAdapter);
        fetchCategoryFromAPI();

        // Inflate the layout for this fragment
        return view;
    }
    private void fetchCategoryFromAPI() {
        String URL = "http://10.0.2.2:8000/api/category";
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("JSON", response);
                        JsonFactory jsonFactory = new JsonFactory();
                        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);

                        try {
                            JsonNode arrayNode = objectMapper.readTree(response);

                            for (JsonNode jsonNode : arrayNode) {
                                mCategory.add(new Category(
                                        jsonNode.get("id").asText(),
                                        jsonNode.get("name").asText(),
                                        jsonNode.get("image").asText()
                                ));
                            }
                            mAdapter.notifyDataSetChanged();

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
