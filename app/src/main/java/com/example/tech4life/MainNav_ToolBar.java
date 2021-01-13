package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainNav_ToolBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav__tool_bar);
    }
    //Event Click
    public void ClickSearch(View view){
        OpenActivity(this,SearchActivity.class);
    }
    public void ClickAnnoucement(View view){
        OpenActivity(this,SearchActivity.class);
    }
    public void OpenActivity(Context context, Class aClass){
        Intent intent = new Intent(context,aClass);
        startActivity(intent);
    }
}