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
import com.example.tech4life.SeriesDetailActivity;
import com.example.tech4life.recycleritems.Post;
import com.example.tech4life.recycleritems.Series;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Series> mSeries;

    public SeriesAdapter(Context mContext, ArrayList<Series> mSeries){
        this.mContext = mContext;
        this.mSeries = mSeries;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mUsername;
        private TextView mDate;
        private TextView mTitle;
        private TextView mContent;
        private CircleImageView mImg;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            mUsername = itemView.findViewById(R.id.txtUsernameSeries);
            mDate = itemView.findViewById(R.id.txtDateSeries);
            mTitle = itemView.findViewById(R.id.txtTitleSeries);
            mContent = itemView.findViewById(R.id.txtContentSeries);
            mImg = itemView.findViewById(R.id.series_item_avt);
        }
    }


    @NonNull
    @Override
    public SeriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View serieView = inflater.inflate(R.layout.series_custom, parent, false);
        final ViewHolder viewHolder = new ViewHolder(serieView);

        serieView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer position = viewHolder.getLayoutPosition();
                final Series series  = mSeries.get(position);

                Intent intent = new Intent(v.getContext(), SeriesDetailActivity.class);
                intent.putExtra("POST_TITLE", series.getmTitle());
                intent.putExtra("POST_DATE", series.getmDate());
                v.getContext().startActivity(intent);
            }
        });
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull SeriesAdapter.ViewHolder holder, int position) {
        Series series = mSeries.get(position);
        holder.mUsername.setText(series.getmUsername());
        holder.mDate.setText(series.getmDate());
        holder.mTitle.setText(series.getmTitle());
        holder.mContent.setText(series.getContent());
        Picasso.get().load("http://10.0.2.2:8000/img/"+series.getmImg()).into(holder.mImg);

    }

    @Override
    public int getItemCount() {
        return mSeries.size();
    }
}
