package com.example.healthassist.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthassist.R;

public class Primary extends AppCompatActivity {

    private String user;
    private int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        Primary.super.onBackPressed();
                        Primary.this.finish();
                        System.exit(0);
                    }
                }).create().show();
    }


}

