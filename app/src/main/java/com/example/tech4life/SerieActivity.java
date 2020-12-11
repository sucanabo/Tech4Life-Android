package com.example.tech4life;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    public void ClickSeries(View view){
        recreate();
    }
    public void ClickAnnouncement(View view){
        MainActivity.redirecActivity(this,AnnouncementActivity.class);
    }
    public void ClickNotification(View view){
        MainActivity.redirecActivity(this,NotificationActivity.class);
    }
    public void ClickMenu(View view){
        MainActivity.openDrawer(drawerLayout);
    }
    public void ClickLogo(View view){
        MainActivity.closeDrawer(drawerLayout);
    }
    public void ClickHome(View view){
        MainActivity.redirecActivity(this,MainActivity.class);
    }
    public void ClickPost(View view){
        MainActivity.redirecActivity(this,Post_Detail.class);
    }
    public void ClickLogout(View view){
        MainActivity.logout(this);
    }
    private void createSeriesList() {
        for (int i = 1; i < 50; i++){
            mSeries.add(new Series("Username" + i, "Date", "Title", "Content", R.drawable.ic_info));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}