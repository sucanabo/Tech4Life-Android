package com.example.tech4life.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.tech4life.PostDetailActivity;
import com.example.tech4life.R;
import com.example.tech4life.recycleritems.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Post> mPost;

    public PostsAdapter(Context mContext, ArrayList<Post> mPost) {
        this.mContext = mContext;
        this.mPost = mPost;
    }

    @NonNull
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View postView = inflater.inflate(R.layout.activity_post_item, parent, false);
        final PostsAdapter.ViewHolder viewHolder = new ViewHolder(postView);

        postView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer position = viewHolder.getLayoutPosition();
                final Post post  = mPost.get(position);

                Intent intent = new Intent(v.getContext(), PostDetailActivity.class);
                intent.putExtra("POST_TITLE", post.getTitle());
                intent.putExtra("POST_DATE", post.getDateCreated());
                intent.putExtra("POST_VOTE", post.getVote());
                intent.putExtra("POST_VIEW", post.getView());
                intent.putExtra("POST_CLIP", post.getClipped());
                intent.putExtra("POST_COMMENT", post.getComment());
                intent.putExtra("POST_CONTENT", post.getContent());
                intent.putExtra("POST_AUTHOR_NAME", post.getAuthorName());

                intent.putExtra("AUTHOR_IMG", post.getAuthorImg());
                intent.putExtra("AUTHOR_NAME", post.getAuthorName());
                intent.putExtra("AUTHOR_USERNAME", post.getAuthorUsername());
                intent.putExtra("ID_POST", post.getId());
                v.getContext().startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        Post post = mPost.get(position);

        holder.tvAuthorName.setText(post.getAuthorName());
        holder.tvPostTitle.setText(post.getTitle());
        holder.tvPostDate.setText(post.getDateCreated());

        Picasso.get().load("http://10.0.2.2:8000/img/img_post/"+post.getImg()).into(holder.ivPostImg);
        Picasso.get().load("http://10.0.2.2:8000/img/"+post.getAuthorImg()).into(holder.ivAuthorImg);
    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivAuthorImg;
        public TextView tvAuthorName;
        public TextView tvPostDate;
        public TextView tvPostTitle;
        public ImageView ivPostImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAuthorImg = itemView.findViewById(R.id.series_item_avt);
            tvAuthorName = itemView.findViewById(R.id.txtUsernameSeries);

            tvPostDate = itemView.findViewById(R.id.txtDateSeries);
            tvPostTitle = itemView.findViewById(R.id.txtTitleSeries);
            ivPostImg = itemView.findViewById(R.id.post_item_img);

        }
    }
}