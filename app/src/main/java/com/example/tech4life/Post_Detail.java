package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Post_Detail extends AppCompatActivity {
    //Drawer variable
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post__detail);
        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);

    }
    //Method
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
        recreate();
    }
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
    public void clickBack(View view){
        
    }
    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}