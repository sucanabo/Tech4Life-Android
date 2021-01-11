package com.example.tech4life;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tech4life.adapter.AnnouncementsAdapter;
import com.example.tech4life.recycleritems.Announcements;

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

    public void ClickLogout(View view){
        //close app
        MainActivity.logout(this);
    }
    private void createAnnouncementsList() {
        for (int i = 1; i < 50; i++){
            mAnnouncements.add(new Announcements("Announcements content example " + i, R.drawable.ic_info));
        }
    }

}