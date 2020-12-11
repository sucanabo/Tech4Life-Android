package com.example.tech4life;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnnouncementActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    private ArrayList<Announcements> mAnnouncements;
    private RecyclerView mRecyclerAnnouncements;
    private AnnouncementsAdapter mAnnouncementsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        mRecyclerAnnouncements = findViewById(R.id.announcementRecyclerView);
        mAnnouncements = new ArrayList<>();
        createAnnouncementsList();
        mAnnouncementsAdapter = new AnnouncementsAdapter(this, mAnnouncements);
        mRecyclerAnnouncements.setAdapter(mAnnouncementsAdapter);
        mRecyclerAnnouncements.setLayoutManager(new LinearLayoutManager(this));

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickAnnouncement(View view){
        recreate();
    }
    public void ClickNotification(View view){
        MainActivity.redirecActivity(this,NotificationActivity.class);
    }
    public void ClickSeries(View view){
        MainActivity.redirecActivity(this,SerieActivity.class);
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
    private void createAnnouncementsList() {
        for (int i = 1; i < 50; i++){
            mAnnouncements.add(new Announcements("Announcements content example " + i, R.drawable.ic_info));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}