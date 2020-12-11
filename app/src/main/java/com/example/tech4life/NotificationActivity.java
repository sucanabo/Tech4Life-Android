package com.example.tech4life;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    private ArrayList<Notifications> mNotifications;
    private RecyclerView mRecyclerNotifications;
    private NotificationsAdapter mNotificationsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mRecyclerNotifications = findViewById(R.id.notificationRecyclerView);
        mNotifications = new ArrayList<>();
        createNotificationsList();
        mNotificationsAdapter = new NotificationsAdapter(this, mNotifications);
        mRecyclerNotifications.setAdapter(mNotificationsAdapter);
        mRecyclerNotifications.setLayoutManager(new LinearLayoutManager(this));

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickNotification(View view){
        recreate();
    }

    public void ClickAnnouncement(View view){
        MainActivity.redirecActivity(this,AnnouncementActivity.class);
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
    private void createNotificationsList() {
        for (int i = 1; i < 50; i++){
            mNotifications.add(new Notifications("Notifications content example " + i, "date" + i, R.drawable.ic_notification));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}