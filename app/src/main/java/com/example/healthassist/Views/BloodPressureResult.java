package com.example.healthassist.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthassist.R;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BloodPressureResult extends AppCompatActivity {

    private String user, Date;
    int SP, DP;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    DateFormat sdf=new SimpleDateFormat("dd,MMM yyyy");
    Date today = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure_result);

        Date = df.format(today);
        TextView TBP = (TextView) this.findViewById(R.id.BPT);
        TextView SBP = findViewById(R.id.SendBP);
        TextView newR=findViewById(R.id.BP_NEW);
        TextView name=findViewById(R.id.HB_NAME);
        TextView dateToday=findViewById(R.id.HB_DATE);



        dateToday.setText(sdf.format(today));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            SP = bundle.getInt("SP");
            DP = bundle.getInt("DP");
            user = bundle.getString("Usr");
            TBP.setText(String.valueOf(SP + " / " + DP+" sp/dp"));
            name.setText("Hi "+user);
        }

        newR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BloodPressureResult.this, Primary.class);
                i.putExtra("Usr", user);
                startActivity(i);
                finish();
            }
        });

        SBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_SEND);
//                i.setType("message/rfc822");
//                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
//                i.putExtra(Intent.EXTRA_SUBJECT, "HealthAssist Vital Report");
//                i.putExtra(Intent.EXTRA_TEXT, user + "'s Blood Pressure " + "\n" + " at " + Date + " is :    " + SP + " / " + DP);
//                try {
//                    startActivity(Intent.createChooser(i, "Send mail..."));
//                } catch (android.content.ActivityNotFoundException ex) {
//                    Toast.makeText(BloodPressureResult.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
//                }
                Intent intent =new Intent(BloodPressureResult.this,ReportActivity.class);
                intent.putExtra("Usr",user);
                intent.putExtra("vitalType",2);
                intent.putExtra("vitalVal",SP+" / "+DP+" sp/dp");
                startActivity(intent);
               // finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(BloodPressureResult.this, Primary.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
