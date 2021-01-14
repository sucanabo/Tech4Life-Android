package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class SeriesDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_detail);
    }
    public void clickBackToPrevious(View view) {

        onBackPressed();
    }
    public void ClickMore(View view){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SeriesDetailActivity.this,R.style.BottomSheetDialogTheme);
        final View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.bottom_sheet_series,(LinearLayout)findViewById(R.id.bottom_sheet_container));
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
    public void ClickReport(View view){
        Toast.makeText(this,"report",Toast.LENGTH_SHORT).show();
    }
}