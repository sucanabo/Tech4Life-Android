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
import com.example.tech4life.recycleritems.Notifications;

import java.util.ArrayList;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Notifications> mNotifications;

    public NotificationsAdapter(Context mContext, ArrayList<Notifications> mNotifications){
        this.mContext = mContext;
        this.mNotifications = mNotifications;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImage;
        private TextView mContent;
        private TextView mDate;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            mImage = itemView.findViewById(R.id.imgNotification);
            mContent = itemView.findViewById(R.id.txtContentNotification);
            mDate = itemView.findViewById(R.id.txtDateNotification);
        }
    }


    @NonNull
    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View notificationView = inflater.inflate(R.layout.notifications_custom, parent, false);
        ViewHolder viewHolder = new ViewHolder(notificationView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.ViewHolder holder, int position) {
        Notifications notifications = mNotifications.get(position);
        holder.mContent.setText(notifications.getContent());
    }

    @Override
    public int getItemCount() {
        return mNotifications.size();
    }
}
