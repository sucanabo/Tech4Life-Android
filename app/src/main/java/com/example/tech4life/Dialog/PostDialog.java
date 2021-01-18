package com.example.tech4life.Dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.tech4life.LoginActivity;
import com.example.tech4life.PostDetailActivity;
import com.example.tech4life.R;
import com.example.tech4life.ReportActivity;
import com.example.tech4life.singleton.AccountSession;
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
                if(AccountSession.getAccount().getId() != null){
                    Intent intent = new Intent(getActivity(),ReportActivity.class);
                    intent.putExtra("POST_ID",mPostId);
                    startActivity(intent);
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    //Set title
                    builder.setTitle("Login required !");
                    //Set mess
                    builder.setMessage("You must be login to do this action.");
                    //Positive yes button
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(getContext(),LoginActivity.class));
                        }
                    });
                    //Negative no button
                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           //Dissmiss dialog
                           dialog.dismiss();
                        }
                    });
                    //Show dialog
                    builder.show();
                }
            }
        });
        return  v;
    }
}