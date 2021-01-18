package com.example.tech4life;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech4life.models.Account;
import com.example.tech4life.singleton.AccountSession;
import com.example.tech4life.synctask.ImageUploadTask;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {

    private CircleImageView avatarImg;
    private static final int IMAGE_REQUEST_CODE = 1;
    private Button browserBtn;
    private Button uploadBtn;
    private Bitmap bitmap;
    private String mUserId = AccountSession.getAccount().getId();

    private EditText username_input;
    private EditText display_input;
    private EditText password_input;
    //number of image select required
    private static final int PICK_IMG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        avatarImg = (CircleImageView) findViewById(R.id.edit_avt);
        browserBtn = (Button) findViewById(R.id.edit_browser_btn);
        uploadBtn = (Button) findViewById(R.id.edit_upload_btn);
        username_input    = (EditText) findViewById(R.id.username_input);
        display_input =(EditText)findViewById(R.id.display_input);
        password_input =  (EditText) findViewById(R.id.password_input);
        //Click event
        browserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageFromGallery();

            }
        });
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImageUploadTask(getApplicationContext()).execute();
            }
        });
        loadData();
    }
    public void selectImageFromGallery(){
        Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("request code: ", String.valueOf(requestCode)+ RESULT_OK);
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            String picturePath="";
            Log.v("************:", "image" + selectedImage);
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
                avatarImg.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            uploadPicture(mUserId,getStringImage(bitmap));
        }


    }
    private String getStringImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageByteArray,Base64.DEFAULT);
        return encodedImage;
    }
    private void uploadPicture(final String mUserId,final   String bitmap) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        String url = "http://10.0.2.2:8000/api/editprofile";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response nha", response);
                        if(response.equals("true")){
                            Toast.makeText( getApplicationContext(),"Edit Success", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText( getApplicationContext(),"Edit fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorClip", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                return params;
            }
        };
        queue.add(postRequest);
    }
    public void ClickSave(View view){
        String url = "http://10.0.2.2:8000/api/editprofile";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response nha", response);
                        if(response.equals("ok")){
                            Toast.makeText(getApplicationContext(),"Update success!",Toast.LENGTH_SHORT);
                            Account account = AccountSession.getAccount();
                            account.setUsername(username_input.getText().toString());
                            account.setDisplayName(display_input.getText().toString());
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Update fail - Password wrong!",Toast.LENGTH_SHORT);
                        }
                        loadData();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Update fail - Error",Toast.LENGTH_SHORT);
                        Log.e("Error", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("username", username_input.getText().toString());
                params.put("display_name", display_input.getText().toString());
                params.put("id", AccountSession.getAccount().getId());
                params.put("password", password_input.getText().toString());
                return params;
            }
        };
        queue.add(postRequest);
    }
    public void ClickCancel(View view){
        loadData();
    }

    private void loadData() {
        Account account = AccountSession.getAccount();
        String accountAvatarLink = "http://10.0.2.2:8000/img/" + account.getAvatar();
        Picasso.get().load(accountAvatarLink).into(avatarImg);
        username_input.setText(account.getUsername());
        display_input.setText(account.getDisplayName());
        password_input.setText("");
    }
}