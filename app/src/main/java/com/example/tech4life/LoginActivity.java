package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech4life.models.Account;
import com.example.tech4life.singleton.AccountSession;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {



    private TextInputLayout loEmail;
    private TextInputLayout loPassword;
    private String URL ="http://10.0.2.2:8000/api/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_screen);
        loEmail    = (TextInputLayout) findViewById(R.id.your_name_login);
        loPassword =  (TextInputLayout) findViewById(R.id.password_login);
        Toast.makeText(LoginActivity.this, "Wellcome Login !!!", Toast.LENGTH_SHORT).show();
    }
    public void handleLoginClickEvent(View view) {
        if(loEmail.getEditText().getText().toString().equals("")){
            Toast.makeText(this,"Email can not be emrty !",Toast.LENGTH_SHORT).show();
        }
        else if(loPassword.getEditText().getText().toString().equals("")){
            Toast.makeText(this,"Password can not be emrty !",Toast.LENGTH_SHORT).show();
        }
        else if (!loEmail.getEditText().getText().toString().equals("") && !loPassword.getEditText().getText().toString().equals(""))
        {
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.d("respone", response);
                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.getString("status").toString();
                                Log.d("Login", status);

                                if (status.equals("success")) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    final String user_name = jsonObject.getString("username").toString();
                                    final String email = jsonObject.getString("email").toString();
                                    final String token = jsonObject.getString("token").toString();
                                    final String display_name = jsonObject.getString("displayname").toString();
                                    final String avatar = jsonObject.getString("avatar").toString();
                                    final String id = jsonObject.getString("id").toString();
                                    Toast.makeText(LoginActivity.this, "\nWellcome !!!" + user_name, Toast.LENGTH_SHORT).show();
                                    AccountSession.setAccount(new Account(user_name, token, display_name, email, avatar, id));
                                    startActivity(intent);
                                }
                                else if(status.equals("failure")){
                                    Toast.makeText(LoginActivity.this,"Login Not Success", Toast.LENGTH_SHORT).show();
                                }
                            if(status.equals("success"))
                            {
                                Log.d("status","sussecced");
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                final String user_name = jsonObject.getString("username").toString();
                                final String email = jsonObject.getString("email").toString();
                                final String token = jsonObject.getString("token").toString();
                                final String display_name = jsonObject.getString("displayname").toString();
                                final String avatar = jsonObject.getString("avatar").toString();
                                final String id = jsonObject.getString("id").toString();
                                Toast.makeText(LoginActivity.this,"\nWellcome !!!" + user_name,Toast.LENGTH_SHORT).show();
                               AccountSession.setAccount(new Account(user_name,token, display_name, email, avatar, id));
                               startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(LoginActivity.this, "App Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", loEmail.getEditText().getText().toString());
                    data.put("password", loPassword.getEditText().getText().toString());
                    return data;
                }
            };
            queue.add(stringRequest);
        }
    }


    public void handlePreviousLoginClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
    public void handleRegisterClickEvent(View view) {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}