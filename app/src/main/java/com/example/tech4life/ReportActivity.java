package com.example.tech4life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech4life.singleton.AccountSession;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class ReportActivity extends AppCompatActivity {
    private String mOption = "";
    private String mPostId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Bundle data = this.getIntent().getExtras();
        mPostId = data.getString("POST_ID");
    }
    public void clickBackToPrevious(View view){
        onBackPressed();
    }
    public void SendReport(View view){
        RadioButton rb1 = (RadioButton) findViewById(R.id.report_spam);
        RadioButton rb2 = (RadioButton) findViewById(R.id.report_rule);
        RadioButton rb3 = (RadioButton) findViewById(R.id.report_hashment);
        RadioButton rb4 = (RadioButton) findViewById(R.id.report_copyright);
        TextInputEditText content = (TextInputEditText) findViewById(R.id.report_content);
        if(rb1.isChecked())
            mOption = "Spam";
        else if(rb2.isChecked())
            mOption = "Rules Violation";
        else if(rb3.isChecked())
            mOption = "Harassment";
        else if(rb4.isChecked())
            mOption = "Infringes Copyright";
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "http://10.0.2.2:8000/api/postpostreport";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("post_id", mPostId);
            jsonBody.put("user_id", AccountSession.getAccount().getId());
            jsonBody.put("reason",mOption);
            jsonBody.put("message",content.getText().toString());
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equals("200")){
                       Toast.makeText(getApplicationContext(),R.string.report_sussecced,Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),R.string.report_fail,Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };
            requestQueue.add(stringRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}