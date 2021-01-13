package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        loadingPostComponents();
        loadingPostData();

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
        Picasso.get().load(authorImgPath).into(authorImg);
        Picasso.get().load(authorImgPath).into(authorLargeImg);
    }
}