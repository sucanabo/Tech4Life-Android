package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech4life.adapter.CategoryAdapter;
import com.example.tech4life.singleton.AccountSession;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class CategoryItem extends AppCompatActivity {

    Button btnFollow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_item);
        btnFollow = findViewById(R.id.btnFollowCategory);
    }

    public void handleCategoryFollow(View view) {

        if ( !AccountSession.isLogon()) {
            Toast.makeText( CategoryItem.this,"Vui lòng đăng nhập trước khi theo dỗi!", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:8000/api/followcategory";
        RequestQueue queue = Volley.newRequestQueue(CategoryItem.this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        JsonFactory jsonFactory = new JsonFactory();
                        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);

                        try {
                            JsonNode arrayNode = objectMapper.readTree(response);

                            if ( arrayNode.get("status").asText() == "followed") {
                                btnFollow.setText("Followed");
                            } else {
                                btnFollow.setText("Follow");
                            }
                        } catch (Exception e) {

                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorClip", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("user_id", AccountSession.getAccount().getId());
                params.put("category_id", btnFollow.getTag(R.string.KEY).toString());

                return params;
            }
        };
        queue.add(postRequest);
    }
}