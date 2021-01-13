package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech4life.adapter.PostsAdapter;
import com.example.tech4life.recycleritems.Post;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private EditText searchInput;
    RecyclerView mRecyclerPost;
    PostsAdapter mPostAdapter;
    ArrayList<Post> mPost = new ArrayList<Post>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //Assign variable
        searchInput = (EditText) findViewById(R.id.search_input);
        searchInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Toast.makeText(SearchActivity.this, searchInput.getText(), Toast.LENGTH_SHORT).show();
                    mPost.removeAll(mPost);
                    fetchPostsFromAPI();

                    return true;
                }
                return false;
            }
        });
        mPostAdapter = new PostsAdapter(this,mPost);
        mRecyclerPost = findViewById(R.id.search_post_recyclerView);
        mRecyclerPost.setAdapter(mPostAdapter);
        mRecyclerPost.setLayoutManager(new LinearLayoutManager(this));
    }
        public void closeBtn(View view){
            searchInput.setText("");
        }
        public void backBnt(View view){
            onBackPressed();
        }
        private void fetchPostsFromAPI() {
        //Log.d("API", "cccccccccccccccccccccccccccccccccc");
        String URL = "http://10.0.2.2:8000/api/searchpost/"+searchInput.getText().toString();
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
}