package com.example.tech4life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tech4life.recycleritems.ClipPost;
import com.example.tech4life.recycleritems.Comment;
import com.example.tech4life.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private ArrayList<Comment> mComment;
    private Context mContext;

    public CommentAdapter(Context mContext, ArrayList<Comment> mComment ) {
        this.mComment = mComment;
        this.mContext = mContext;
    }

    @NonNull
    @Override

    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View commentItemView = inflater.inflate(R.layout.activity_comment_item, parent, false);
        CommentAdapter.ViewHolder viewHolder = new CommentAdapter.ViewHolder(commentItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comment comment = mComment.get(position);

        holder.tvAuthorName.setText(comment.getAuthorName());
        holder.tvContent.setText(comment.getContent());
        holder.tvPublishDate.setText(comment.getCreateDate());
        Picasso.get().load("http://10.0.2.2:8000/img/"+comment.getAuthorImg()).into(holder.ivAuthorImg);
    }

    @Override
    public int getItemCount() {
        return mComment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView ivAuthorImg;
        public TextView tvAuthorName;
        public TextView tvPublishDate;
        public TextView tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAuthorImg = itemView.findViewById(R.id.comment_author_avt);
            tvAuthorName = itemView.findViewById(R.id.comment_author_name);
            tvPublishDate = itemView.findViewById(R.id.comment_publish_date);
            tvContent = itemView.findViewById(R.id.comment_content);
        }
    }
}
