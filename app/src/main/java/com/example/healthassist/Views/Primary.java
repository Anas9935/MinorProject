package com.example.healthassist.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthassist.GraphView.GraphActivity;
import com.example.healthassist.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Primary extends AppCompatActivity {

    private String user;
    private int p;
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_app_bar);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        BottomNavigationBar();
        ImageButton HeartRate = this.findViewById(R.id.HR);
        ImageButton BloodPressure = this.findViewById(R.id.BP);
        ImageButton Ox2 = this.findViewById(R.id.O2);
        ImageButton RRate = this.findViewById(R.id.RR);
        ImageButton VitalSigns = this.findViewById(R.id.VS);
        ImageButton Abt = this.findViewById(R.id.About);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("Usr");
            //The key argument here must match that used in the other activity
        }

        Abt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AboutApp.class);
                Primary.this.startActivity(i);
                Primary.this.finish();
            }
        });


        //Every Test Button sends the username + the test number, to go to the wanted test after the instructions activity
        HeartRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = 1;
                Intent i = new Intent(v.getContext(), StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                Primary.this.startActivity(i);
                Primary.this.finish();
            }
        });

        BloodPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = 2;
                Intent i = new Intent(v.getContext(), StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                Primary.this.startActivity(i);
                Primary.this.finish();
            }
        });

        RRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = 3;
                Intent i = new Intent(v.getContext(), StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                Primary.this.startActivity(i);
                Primary.this.finish();
            }
        });

        Ox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = 4;
                Intent i = new Intent(v.getContext(), StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                Primary.this.startActivity(i);
                Primary.this.finish();

            }
        });

        VitalSigns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = 5;
                Intent i = new Intent(v.getContext(), StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                Primary.this.startActivity(i);
                Primary.this.finish();
            }
        });
    }
    private void BottomNavigationBar()
    {
        bottomNavigationView.getMenu().findItem(R.id.action_test).setChecked(true);
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
                        break;
                    case R.id.action_profile :
                        intent = new Intent(view.getContext(), ProfileActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        break;
                    case R.id.action_report:
                        break;
                }
                return true;
            }
        });
    }

}

