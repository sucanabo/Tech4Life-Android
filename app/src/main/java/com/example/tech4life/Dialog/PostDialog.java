package com.example.tech4life.Dialog;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tech4life.PostDetailActivity;
import com.example.tech4life.R;
import com.example.tech4life.ReportActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PostDialog extends BottomSheetDialogFragment {

    private String mPostId;
    public PostDialog(String post_id) {
        this.mPostId = post_id;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_post,container,false);
        ImageView report = (ImageView)v.findViewById(R.id.post_report);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ReportActivity.class);
                intent.putExtra("POST_ID",mPostId);
                startActivity(intent);
            }
        });
        return  v;
    }
}