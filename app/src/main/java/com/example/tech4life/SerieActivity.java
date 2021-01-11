package com.example.tech4life;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tech4life.adapter.SeriesAdapter;
import com.example.tech4life.recycleritems.Series;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class SerieActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    private ArrayList<Series> mSeries;
    private RecyclerView mRecyclerSeries;
    private SeriesAdapter mSeriesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie);
        mRecyclerSeries = findViewById(R.id.serieRecyclerView);
        mSeries = new ArrayList<>();
        createSeriesList();
        mSeriesAdapter = new SeriesAdapter(this, mSeries);
        mRecyclerSeries.setAdapter(mSeriesAdapter);
        mRecyclerSeries.setLayoutManager(new LinearLayoutManager(this));

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickLogout(View view){
        //close app
        MainActivity.logout(this);
    }
    private void createSeriesList() {
        for (int i = 1; i < 50; i++){
            mSeries.add(new Series("Username" + i, "Date", "Title", "Content", R.drawable.ic_info));
        }
    }



    public static class Post_Detail extends AppCompatActivity {
        //Drawer variable
        DrawerLayout drawerLayout;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_post_detail);
            //Assign variable
            drawerLayout = findViewById(R.id.drawer_layout);

        }
        //Method
        public void ClickLogout(View view){
            MainActivity.logout(this);
        }
        public void ClickMore(View view){
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Post_Detail.this,R.style.BottomSheetDialogTheme);
            final View bottomSheetView = LayoutInflater.from(getApplicationContext())
                    .inflate(R.layout.bottom_sheet_post,(LinearLayout)findViewById(R.id.bottom_sheet_container));
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        }
    }
}