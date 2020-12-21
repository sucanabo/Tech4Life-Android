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
import com.example.tech4life.recycleritems.Series;

import java.util.ArrayList;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Series> mSeries;

    public SeriesAdapter(Context mContext, ArrayList<Series> mSeries){
        this.mContext = mContext;
        this.mSeries = mSeries;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImage;
        private TextView mUsername;
        private TextView mDate;
        private TextView mTitle;
        private TextView mContent;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            mImage = itemView.findViewById(R.id.imgSeries);
            mUsername = itemView.findViewById(R.id.txtUsernameSeries);
            mDate = itemView.findViewById(R.id.txtDateSeries);
            mTitle = itemView.findViewById(R.id.txtTitleSeries);
            mContent = itemView.findViewById(R.id.txtContentSeries);
        }
    }


    @NonNull
    @Override
    public SeriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View serieView = inflater.inflate(R.layout.series_custom, parent, false);
        ViewHolder viewHolder = new ViewHolder(serieView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull SeriesAdapter.ViewHolder holder, int position) {
        Series series = mSeries.get(position);
        holder.mContent.setText(series.getContent());
    }

    @Override
    public int getItemCount() {
        return mSeries.size();
    }
}
