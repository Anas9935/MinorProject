package com.example.healthassist.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthassist.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RespirationResult extends AppCompatActivity {

    private String user, Date;
    int RR;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    DateFormat sdf=new SimpleDateFormat("dd,MMM yyyy");
    Date today = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respiration_result);

        Date = df.format(today);
        TextView RRR = (TextView) this.findViewById(R.id.RRR);
        TextView SRR =findViewById(R.id.SendRR);
        TextView newRead=findViewById(R.id.RR_NEW);
        TextView name=findViewById(R.id.HB_NAME);
        TextView dateToday=findViewById(R.id.HB_DATE);

        dateToday.setText(sdf.format(today));




        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            RR = bundle.getInt("bpm");
            user = bundle.getString("Usr");
            RRR.setText(String.valueOf(RR)+" bmp");

            name.setText("Hi "+user);

        }

        SRR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_SEND);
//                i.setType("message/rfc822");
//                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
//                i.putExtra(Intent.EXTRA_SUBJECT, "Health Watcher");
//                i.putExtra(Intent.EXTRA_TEXT, user + "'s Respiration Rate " + "\n" + " at " + Date + " is :  " + RR);
//                try {
//                    RespirationResult.this.startActivity(Intent.createChooser(i, "Send mail..."));
//                } catch (android.content.ActivityNotFoundException ex) {
//                    Toast.makeText(RespirationResult.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
//                }

                Intent intent =new Intent(RespirationResult.this,ReportActivity.class);
                intent.putExtra("Usr",user);
                intent.putExtra("vitalType",3);
                intent.putExtra("vitalVal",RR+" bpm");
                startActivity(intent);
                finish();
            }
        });
        newRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RespirationResult.this, Primary.class);
                i.putExtra("Usr", user);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(RespirationResult.this, Primary.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
        super.onBackPressed();

    }
}
