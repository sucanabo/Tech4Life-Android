package com.example.tech4life;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;

public class PostItemAdapter extends RecyclerView.Adapter<PostItemAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<Post_Item> mPost_items;
    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(int pos);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mListener = onItemClickListener;
    }
    public PostItemAdapter(Context mContext,ArrayList<Post_Item> mPost_items){
        this.mContext = mContext;
        this.mPost_items = mPost_items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View post_itemView = inflater.inflate(R.layout.post_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(post_itemView,mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post_Item post_item = mPost_items.get(position);
        holder.mTitle.setText(post_item.getTitle());
        holder.mCreateDate.setText(post_item.getCreateDate().toString());
        Glide.with(mContext)
                .load(post_item.getImg())
                .into(holder.mImg);
        Glide.with(mContext)
                .load(post_item.getAvatar())
                .into(holder.mAvatar);
        holder.mAuthor.setText(post_item.getAuthor());
    }

    @Override
    public int getItemCount() {
        return mPost_items.size();
    }
    public  static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTitle;
        public ImageView mAvatar;
        public TextView mAuthor;
        public ImageView mImg;
        public TextView mCreateDate;
        public ViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);
            mTitle = itemView.findViewById(R.id.post_item_title);
            mAvatar = itemView.findViewById(R.id.post_item_avt);
            mAuthor = itemView.findViewById(R.id.post_item_author);
            mImg = itemView.findViewById(R.id.post_item_img);
            mCreateDate = itemView.findViewById(R.id.post_item_createDate);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            listener.onItemClick(pos);
                        }
                    }
                }
            });
        }

    }
}
