package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

	private String pro = "cc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setContentView(R.layout.report_screen);
//        setContentView(R.layout.login_activity_screen);
        setContentView(R.layout.register_activity_screen);
    }
}