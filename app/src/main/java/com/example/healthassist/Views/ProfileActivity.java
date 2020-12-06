package com.example.healthassist.Views;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.healthassist.GraphView.GraphActivity;
import com.example.healthassist.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
public class ProfileActivity extends AppCompatActivity
{
    TextView name,username,email,gender,age,height,phone;
    UserDB check = new UserDB(this);
    SharedPreferences preferences;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_app_bar);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        BottomNavigationBar();
        preferences=getSharedPreferences("loginPrefs", MODE_PRIVATE);
        String user_name=preferences.getString("username","");
        name=findViewById(R.id.user_name_edit);
        username=findViewById(R.id.username_edit);
        email=findViewById(R.id.user_email_edit);
        age=findViewById(R.id.user_age_edit);
        height=findViewById(R.id.user_height_edit);
        gender=findViewById(R.id.user_gender_edit);
        phone=findViewById(R.id.user_phone_no);
        name.setText(check.getname(user_name));
        email.setText(check.getemail(user_name));
        phone.setText(check.getphone(user_name));
        username.setText(user_name);
        age.setText(check.getage(user_name));
        height.setText(check.getheight(user_name));
        gender.setText(check.getgender(user_name));
    }
    private void BottomNavigationBar()
    {
        bottomNavigationView.getMenu().findItem(R.id.action_profile).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                View view = findViewById(R.id.bottom_navigation);
                Intent intent;
                switch (item.getItemId())
                {
                    case R.id.action_home:
                        intent = new Intent(view.getContext(), GraphActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_test:
                        intent = new Intent(view.getContext(), Primary.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        break;
                    case R.id.action_profile :
                        break;
                    case R.id.action_report:
                        break;
                }
                return true;
            }
        });
    }

}
