package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tech4life.adapter.ClipPostAdapter;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }
    public void ClickEdit(View view){
        startActivity(new Intent(this,EditProfile.class));
    }
    public void lauchClipPostActivity(View view) {
        Intent intent = new Intent(this, ClipPostActivity.class);
        startActivity(intent);
    }
}