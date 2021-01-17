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

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout loDisplayName;
    private TextInputLayout loUserName;
    private TextInputLayout loYourName;
    private TextInputLayout loEmail;
    private TextInputLayout loPassword;
    private TextInputLayout loConfirmPassword;
    private String URL ="http://10.0.2.2:8000/api/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity_screen);
        loYourName = (TextInputLayout) findViewById(R.id.your_name_login);
        loUserName = (TextInputLayout) findViewById(R.id.username_register);
        loEmail    = (TextInputLayout) findViewById(R.id.your_emaiil_adress_register);
        loPassword =  (TextInputLayout) findViewById(R.id.password_register);
        loConfirmPassword = (TextInputLayout) findViewById(R.id.confirm_register);
        Toast.makeText(RegisterActivity.this, "User Register", Toast.LENGTH_SHORT).show();
    }

    public void handleRegisterClickEvent(View view) {

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status").toString();
                            Log.d("Login",status);

                            if(status.equals("success"))
                            {
                                Toast.makeText(RegisterActivity.this,"Register Successs",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(RegisterActivity.this,"Register Failure",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this,"App Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("username", loUserName.getEditText().getText().toString());
                data.put("display_name", loYourName.getEditText().getText().toString());
                data.put("email", loEmail.getEditText().getText().toString());
                data.put("password", loPassword.getEditText().getText().toString());
                return data;
            }
        };
        queue.add(stringRequest);
    }
}