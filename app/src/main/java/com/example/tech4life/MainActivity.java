package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {
    
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      setContentView(R.layout.activity_main);



        //setContentView(R.layout.report_screen);
        //setContentView(R.layout.login_activity_screen);
        //setContentView(R.layout.register_activity_screen);
        //setContentView(R.layout.post_activity_screen);

//        setContentView(R.layout.user_activity_screen);

        //setContentView(R.layout.user_activity_screen);


        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
    }
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
    public void ClickHome(View view){
        //recreate activity
        recreate();
    }
    public void ClickPost(View view){
        //redirect activity to post
        redirecActivity(this,Post_Detail.class);
    }
//    public void ClickSeries(View view){
//        //redirect activity to series
//        redirecActivity(this,);
//    }
//    public void ClickCategory(View view){
//        //redirect activity to category
//        redirecActivity(this,);
//    }
//    public void ClickNotification(View view){
//        //redirect activity to noitification
//        redirecActivity(this,);
//    }
    public void ClickAnnouncement(View view){
        //redirect activity to announcement
        redirecActivity(this, AnnouncementActivity.class);
    }
//    public void ClicksSetting(View view){
//        //redirect activity to setting
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