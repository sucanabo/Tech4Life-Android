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
import com.example.tech4life.recycleritems.Announcements;

import java.util.ArrayList;

public class AnnouncementsAdapter extends RecyclerView.Adapter<AnnouncementsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Announcements> mAnnouncements;

    public AnnouncementsAdapter(Context mContext, ArrayList<Announcements> mAnnouncements){
        this.mContext = mContext;
        this.mAnnouncements = mAnnouncements;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImage;
        private TextView mContent;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            mImage = itemView.findViewById(R.id.btnBacl);
            mContent = itemView.findViewById(R.id.txtUsernameSeries);
        }
    }


    @NonNull
    @Override
    public AnnouncementsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View announcementView = inflater.inflate(R.layout.activity_announcement, parent, false);
        ViewHolder viewHolder = new ViewHolder(announcementView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementsAdapter.ViewHolder holder, int position) {
        Announcements announcements = mAnnouncements.get(position);
        holder.mContent.setText(announcements.getContent());
    }

    @Override
    public int getItemCount() {
        return mAnnouncements.size();
    }
}
