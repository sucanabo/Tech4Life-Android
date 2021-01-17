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

import com.example.tech4life.PostDetailActivity;
import com.example.tech4life.R;
import com.example.tech4life.recycleritems.ClipPost;
import com.example.tech4life.recycleritems.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ClipPostAdapter extends RecyclerView.Adapter<ClipPostAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ClipPost> mClipPost;

    public ClipPostAdapter(Context mContext, ArrayList<ClipPost> mClipPost) {
        this.mContext = mContext;
        this.mClipPost = mClipPost;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivAuthorImg;
        public TextView tvAuthorName;
        public TextView tvClipPostDate;
        public TextView tvClipPostTitle;
        public ImageView ivClipPostImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAuthorImg = itemView.findViewById(R.id.clip_author_avt);
            tvAuthorName = itemView.findViewById(R.id.clip_author_name);

            tvClipPostDate = itemView.findViewById(R.id.clip_date);
            tvClipPostTitle = itemView.findViewById(R.id.txt_title_clip);
            ivClipPostImg = itemView.findViewById(R.id.clip_item_img);

        }
    }

    @NonNull
    @Override
    public ClipPostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View clipPostView = inflater.inflate(R.layout.activity_clip_post_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(clipPostView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClipPostAdapter.ViewHolder holder, int position) {
        ClipPost clipPost = mClipPost.get(position);

        holder.tvAuthorName.setText(clipPost.getAuthorName());
        holder.tvClipPostTitle.setText(clipPost.getTitle());
        holder.tvClipPostDate.setText(clipPost.getDateCreated());

        Picasso.get().load("http://10.0.2.2:8000/img/img_post/"+clipPost.getImg()).into(holder.ivClipPostImg);
        Picasso.get().load("http://10.0.2.2:8000/img/"+clipPost.getAuthorImg()).into(holder.ivAuthorImg);
    }

    @Override
    public int getItemCount() {
        return mClipPost.size();
    }


}
