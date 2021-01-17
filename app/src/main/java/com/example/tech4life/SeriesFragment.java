package com.example.tech4life;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.tech4life.adapter.SeriesAdapter;

import com.example.tech4life.recycleritems.Series;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;


public class SeriesFragment extends Fragment {
    RecyclerView mRecyclerSeries;
    SeriesAdapter mSeriesAdapter;
    ArrayList<Series> mSeries = new ArrayList<Series>();
    public SeriesFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mSeriesAdapter = new SeriesAdapter(getContext(), mSeries);
        //Assign variable
        View view = inflater.inflate(R.layout.fragment_series,container,false);

        mRecyclerSeries = (RecyclerView) view.findViewById(R.id.series_recycler_view);
        mRecyclerSeries.setHasFixedSize(true);
        mRecyclerSeries.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerSeries.setAdapter(mSeriesAdapter);
        mRecyclerSeries.setAdapter(mSeriesAdapter);
        fetchSeriesFromAPI();

        // Inflate the layout for this fragment
        return view;
    }

    private void fetchSeriesFromAPI() {
        //Log.d("API", "cccccccccccccccccccccccccccccccccc");
        String URL = "http://10.0.2.2:8000/api/series";
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
                                mSeries.add(new Series(
                                        jsonNode.get("username").asText(),
                                        jsonNode.get("created_at").asText(),
                                        jsonNode.get("title").asText(),
                                        jsonNode.get("description").asText(),
                                        jsonNode.get("avatar").asText()
                                ));
                            }
                            mSeriesAdapter.notifyDataSetChanged();

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