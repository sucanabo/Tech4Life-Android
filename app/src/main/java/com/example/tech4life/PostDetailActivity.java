package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech4life.Dialog.PostDialog;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

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

    private String countView;
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        loadingPostComponents();
        loadingPostData();
        updateView();
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

        countView = data.getString("POST_VIEW");
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
    public void ClickMore(View view){
        new PostDialog("5").show(getSupportFragmentManager(),"Dialog");
    }

    public void updateView() {
         mCount = Integer.parseInt(countView) + 1;
         countView = String.valueOf(mCount);

        String url = "http://10.0.2.2:8000/api/updateView/"+postId;
        RequestQueue queue = Volley.newRequestQueue(PostDetailActivity.this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response nha", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
//                params.put("view", countView);


                return params;
            }
        };
        queue.add(postRequest);
    }

    public void clickClipPost(View view) {
        String url = "http://10.0.2.2:8000/api/clipPost";
        Log.d("post_id_nha", postId);
        Log.d("count_nha", countView);
        RequestQueue queue = Volley.newRequestQueue(PostDetailActivity.this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response nha", response);
                        if(response.equals("true")){
                            Toast.makeText( PostDetailActivity.this,"Ghim thành công", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText( PostDetailActivity.this,"Ghim thất bại", Toast.LENGTH_SHORT).show();
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
                params.put("user_id", "1");
                params.put("post_id", postId);

                return params;
            }
        };
        queue.add(postRequest);
    }

}