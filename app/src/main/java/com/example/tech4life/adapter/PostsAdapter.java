package com.example.tech4life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        PostsAdapter.ViewHolder viewHolder = new ViewHolder(postView);
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
