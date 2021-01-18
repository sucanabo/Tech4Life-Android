package com.example.tech4life.synctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.tech4life.EditProfile;

import org.apache.http.HttpConnection;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ImageUploadTask extends AsyncTask<Void,Void,String> {
    private Context mContext;
    private String imageStorage = "http://10.0.2.2:8000/img/";
    private ProgressDialog progressDialog = new ProgressDialog(mContext);

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Updating...");
        progressDialog.show();
    }

    public ImageUploadTask(Context context){
        mContext = context;
    }

    @Override
    protected String doInBackground(Void... voids) {

//        try {
//            URL url = new URL(imageStorage);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Connection", "Keep-Alive");
//            MultipartEntity entity = new MultipartEntity(
//                    HttpMultipartMode.BROWSER_COMPATIBLE);
//
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        } catch (MalformedURLException | ProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
