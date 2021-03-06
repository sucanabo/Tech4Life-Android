package com.example.tech4life;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech4life.adapter.AnnouncementsAdapter;
import com.example.tech4life.adapter.PostsAdapter;
import com.example.tech4life.recycleritems.Announcements;
import com.example.tech4life.recycleritems.Post;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class AnnoucementFragment extends Fragment {
    RecyclerView mRecyclerAnnouncement;
    AnnouncementsAdapter mAnnouncementAdapter;
    ArrayList<Announcements> mAnnouncement = new ArrayList<Announcements>();
    public AnnoucementFragment() {
        // Required empty public constructor
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


        mAnnouncementAdapter = new AnnouncementsAdapter(getContext(),mAnnouncement);
        mRecyclerAnnouncement = view.findViewById(R.id.post_recyclerView);
        mRecyclerAnnouncement.setHasFixedSize(true);
        mRecyclerAnnouncement.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerAnnouncement.setAdapter(mAnnouncementAdapter);
        mRecyclerAnnouncement.setAdapter(mAnnouncementAdapter);
        fetchAnnouncementFromAPI();

        // Inflate the layout for this fragment
        return view;
    }
    private void fetchAnnouncementFromAPI() {
        String URL = "http://10.0.2.2:8000/api/announcement";
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
                                mAnnouncement.add(new Announcements(
                                        jsonNode.get("title").asText(),
                                        R.drawable.ic_info,
                                        jsonNode.get("created_at").asText()
                                ));
                            }
                            mAnnouncementAdapter.notifyDataSetChanged();

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