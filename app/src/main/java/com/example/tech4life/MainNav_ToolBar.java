package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;

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
        Intent intent= new Intent(this,SearchActivity.class);
        startActivity(intent);
    }
}