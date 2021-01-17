package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accounts.AccountsException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech4life.Dialog.PostDialog;
import com.example.tech4life.adapter.CommentAdapter;
import com.example.tech4life.models.Account;
import com.example.tech4life.recycleritems.Comment;
import com.example.tech4life.recycleritems.Post;
import com.example.tech4life.singleton.AccountSession;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostDetailActivity extends AppCompatActivity {

    private TextView postTitle;
    private TextView postDate;
    private TextView postView;
    private TextView postVote;
    private TextView postComment;
    private WebView postContent;

    private ImageView authorLargeImg;
    private TextView authorName;
    private TextView authorLargeName;
    private ImageView authorImg;
    private TextView authorUsername;

    private String postId;

    private RecyclerView rvComment;
    private CommentAdapter mAdapter;
    private ArrayList<Comment> mComment = new ArrayList<Comment>();

    private EditText etCommentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        etCommentContent = findViewById(R.id.etCommentInput);
        rvComment = findViewById(R.id.rvListComment);
        mAdapter = new CommentAdapter(this, mComment);
        rvComment.setLayoutManager(new LinearLayoutManager(this));
        rvComment.setAdapter(mAdapter);

        loadingPostComponents();
        loadingPostData();
        loadPostComemnt();

    }
    public void clickBackToPrevious(View view) {

        onBackPressed();
    }

    public void loadingPostComponents() {
        postTitle = findViewById(R.id.txt_postTitle);
        postDate = findViewById(R.id.txt_post_date);
        postView = findViewById(R.id.txt_view);
        postVote = findViewById(R.id.txt_vote);
        postComment = findViewById(R.id.txt_comment);
        postContent = findViewById(R.id.post_content);

        authorLargeName = findViewById(R.id.author_name);
        authorUsername = findViewById(R.id.author_username);
        authorName = findViewById(R.id.post_author_name);

        authorImg = findViewById(R.id.post_author_img);
        authorLargeImg = findViewById(R.id.author_img);


    }
    public void loadingPostData() {
        Bundle data = this.getIntent().getExtras();
        String authorImgPath = "http://10.0.2.2:8000/img/"+data.getString("AUTHOR_IMG");

        postTitle.setText(data.getString("POST_TITLE"));
        postDate.setText(data.getString("POST_DATE"));
        postView.setText(data.getString("POST_VIEW"));
        postComment.setText(data.getString("POST_COMMENT"));
        postVote.setText(data.getString("POST_VOTE"));
        postContent.loadData(data.getString("POST_CONTENT"), "text/html; charset=utf-8", "UTF-8");
        authorName.setText(data.getString("AUTHOR_NAME"));
        authorLargeName.setText(data.getString("POST_AUTHOR_NAME"));

        postId = data.getString("POST_ID");

        Picasso.get().load(authorImgPath).into(authorImg);
        Picasso.get().load(authorImgPath).into(authorLargeImg);
    }
    public void loadPostComemnt() {

        String URL = "http://10.0.2.2:8000/api/getcomment/"+postId;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("API", response );
                        Log.d("POST", postId );
                        JsonFactory jsonFactory = new JsonFactory();
                        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);

                        try {
                            JsonNode arrayNode = objectMapper.readTree(response);

                            for (JsonNode jsonNode : arrayNode) {
                                mComment.add(new Comment(
                                        jsonNode.get("avatar").asText(),
                                        jsonNode.get("display_name").asText(),
                                        jsonNode.get("publish_date").asText(),
                                        jsonNode.get("content").asText()
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
    public void ClickMore(View view){
        new PostDialog("5").show(getSupportFragmentManager(),"Dialog");
    }

    public void clickClipPost(View view) {
    }

    public void handlePostCommentClickEvent(View view) {

        if ( etCommentContent.getText().length() < 3) {
            Toast.makeText(this,"Vui lòng nhập nhiều hơn 3 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!AccountSession.isLogon()) {
            Toast.makeText(this,"Vui lòng đăng nhập trước khi bình luận", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "http://10.0.2.2:8000/api/putcomment";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("JSON", response);
                            JsonFactory jsonFactory = new JsonFactory();
                            ObjectMapper objectMapper = new ObjectMapper(jsonFactory);
                            JsonNode arrayNode = objectMapper.readTree(response);

                            Toast.makeText(PostDetailActivity.this, "Đã đăng bình luận", Toast.LENGTH_SHORT).show();
                            final String displayname = arrayNode.get("display_name").asText();
                            final String publish_date = arrayNode.get("publish_date").asText();
                            final String avatar = arrayNode.get("avatar").asText();
                            final String content = arrayNode.get("content").asText();

                            mComment.add(0, new Comment(avatar,displayname, publish_date,content));
                            mAdapter.notifyItemInserted(0);

                            return;

                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                            Toast.makeText(PostDetailActivity.this, "App Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PostDetailActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("content", etCommentContent.getText().toString());
                data.put("user_id", AccountSession.getAccount().getId());
                data.put("post_id", postId);
                return data;
            }
        };
        queue.add(stringRequest);
        return;
    }
    private void fetchPostComment() {

    }
}

