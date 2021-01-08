package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        //recycler post item
    }

//    private void createPostItem() {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        Date date = null;
//        String strDate;
//        try {
//            date = formatter.parse("12/12/2020");
//            strDate = formatter.format(date);
//        } catch (ParseException e) {
//            return;
//        }
//        for(int i =0; i<10; i++){
//            mPostItem.add(new Post_Item("Title " + i,R.drawable.avt,"Dustin Dinh",R.drawable.img1,strDate));
//        }
//    }
    public void ClickMenu(View view){
        //Open Drawer
        openDrawer(drawerLayout);
    }
    public static void openDrawer(DrawerLayout drawerLayout){
        //Open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        //Close drawer
        closeDrawer(drawerLayout);
    }
    public static void closeDrawer(DrawerLayout drawerLayout){
        //Close drawer layout
        //Check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            //When drawer is open
            //Close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickPost(View view){
        recreate();
    }
    public void ClickSeries(View view){
        redirecActivity(this,SerieActivity.class);
    }
    public void ClickCategory(View view){
        redirecActivity(this, Locale.Category.class);
    }
    public void ClickNotification(View view){
        redirecActivity(this,NotificationActivity.class);
    }
    public void ClickAnnouncement(View view){
        redirecActivity(this,AnnouncementActivity.class);
    }
//    public void ClickSetting(View view){
//        redirecActivity(this,);
//    }
    public void ClickLogout(View view){
        //close app
        logout(this);
    }
    public static void logout(final Activity activity){
        //redirect activity to setting
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //Set title
        builder.setTitle("Logout");
        //Set mess
        builder.setMessage("Are you sure you want to logout?");
        //Positive yes button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                activity.finishAffinity();
                //Exit app
                System.exit(0);
            }
        });
        //Negative no button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               //Dissmiss dialog
               dialog.dismiss();
            }
        });
        //Show dialog
        builder.show();
    }

    public static void redirecActivity(Activity activity, Class aClass) {
        //Initialize intent
        Intent intent = new Intent(activity,aClass);
        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        closeDrawer(drawerLayout);
    }
}