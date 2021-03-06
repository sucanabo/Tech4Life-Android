package com.example.tech4life;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech4life.adapter.PostsAdapter;
import com.example.tech4life.models.Account;
import com.example.tech4life.recycleritems.Notifications;
import com.example.tech4life.recycleritems.Post;
import com.example.tech4life.singleton.AccountSession;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private MainActivity instance;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    Fragment fragment;

    CircleImageView navUserAvatar;
    TextView navUserDisplayname;
    TextView navUserUsername;

    MenuItem btnToggle;
    Button btnFollow;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
        //Drawer

        drawerLayout = findViewById(R.id.drawer_layout);
        btnFollow = findViewById(R.id.btnFollowCategory);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        final Menu menuNav = navigationView.getMenu();
        btnToggle = menuNav.findItem(R.id.menu_logout);
        navUserAvatar = (CircleImageView) header.findViewById(R.id.nav_avatar);
        navUserDisplayname = (TextView) header.findViewById(R.id.nav_displayname);
        navUserUsername = (TextView) header.findViewById(R.id.nav_username);
        final MenuItem followItem = menuNav.findItem(R.id.menu_following);
        final MenuItem postItem = menuNav.findItem(R.id.menu_post);
        bindingAuthUserToComponents();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.menu_post:
                        fragment = new PostFragment();
                        //returnActivityStat(savedInstanceState);
                        loadFragment(fragment);
                        break;
                    case R.id.menu_series:
                        fragment = new SeriesFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.menu_following:
                        if (!AccountSession.isLogon()) {
                            navigationView.invalidate();
                            fragment = new PostFragment();
                            loadFragment(fragment);
                            Toast.makeText(instance, "Bạn cần phải đăng nhập trước", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        fragment = new PostFollowingFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.menu_tags:
                        fragment = new CategoryFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.menu_annoucement:
                        fragment = new AnnoucementFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.menu_setting:
                        fragment = new SettingFragment();
                        loadFragment(fragment);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
        loadFragment(new PostFragment());
    }

    //load Fragment
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }


    public void lauchUserActivity(View view){
        if(AccountSession.getAccount().getTOKEN() == null){
            Toast.makeText(this,"Vui lòng đăng nhập !", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        }

    }
    public void ClickSearch(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void launchUserActivity(View view) {
        startActivity(new Intent(this, UserActivity.class));
    }
    public static void handleLogoutClickEvent(final Activity activity) {

        //redirect activity to setting
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        //Set title
//        builder.setTitle("Logout");
//        //Set mess
//        builder.setMessage("Are you sure you want to logout?");
//        //Positive yes button
//        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //Finish activity
//                activity.finishAffinity();
//                //Exit app
//                System.exit(0);
//            }
//        });
//        //Negative no button
//        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//               //Dissmiss dialog
//               dialog.dismiss();
//            }
//        });
//        //Show dialog
//        builder.show();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void bindingAuthUserToComponents() {
        if (AccountSession.isLogon()) {
            Account account = AccountSession.getAccount();
            String accountAvatarLink = "http://10.0.2.2:8000/img/" + account.getAvatar();
            Picasso.get().load(accountAvatarLink).into(navUserAvatar);
            navUserUsername.setText("@" + account.getUsername());
            navUserDisplayname.setText(account.getDisplayName());
            btnToggle.setTitle("Logout");
            btnToggle.setIcon(getResources().getDrawable(R.drawable.ic_logout));
        }
    }

    private void clearAuthUserToComponents() {
        navUserAvatar.setImageDrawable(getResources().getDrawable(R.drawable.empty_avatar));
        navUserUsername.setText("@You.Are.Not.Logon");
        navUserDisplayname.setText("Guest");
        AccountSession.clearSesstion();
        btnToggle.setTitle("Login / Register");
        btnToggle.setIcon(getResources().getDrawable(R.drawable.common_full_open_on_phone));
    }

    public void handleLogoutClickEvent(MenuItem item) {

        if ( !AccountSession.isLogon()) {
            startActivity(new Intent(this, LoginActivity.class));
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2:8000/api/logout",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status").toString();
                            Log.d("Logout", status);

                            if (status.equals("success")) {
                                Toast.makeText(MainActivity.this, "Logout Success", Toast.LENGTH_SHORT).show();

                                clearAuthUserToComponents();
                                loadFragment(new PostFragment());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "App Error", Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "ERROR"+error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("token", AccountSession.getAccount().getTOKEN());
                return data;
            }
        };
        queue.add(stringRequest);

    }
    public void handleCategoryFollow(final View view) {

        if ( !AccountSession.isLogon()) {
            Toast.makeText( this,"Vui lòng đăng nhập trước khi theo dỗi!", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:8000/api/followcategory";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        Log.d("JSON", response);
                        JsonFactory jsonFactory = new JsonFactory();
                        ObjectMapper objectMapper = new ObjectMapper(jsonFactory);

                        try {
                            JsonNode arrayNode = objectMapper.readTree(response);

                            if ( arrayNode.get("status").asText() == "followed") {
                                ((Button)view).setText("Followed");
                                ((Button)view).setTextColor(255);
                            } else {
                                ((Button)view).setText("Follow");
                            }
                        } catch (Exception e) {

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
                params.put("user_id", AccountSession.getAccount().getId());
                params.put("category_id", ((Button)view).getTag(R.string.KEY).toString());

                return params;
            }
        };
        queue.add(postRequest);
    }
}
