package com.example.healthassist.LoginActivities;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.healthassist.R;
import com.example.healthassist.Views.First;
import com.example.healthassist.Views.Login;

public class IntroScreen extends AppCompatActivity
{
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        getSupportActionBar().hide();
        TextView sq=findViewById(R.id.scanqr);
        TextView signin=findViewById(R.id.signin);
        sq.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(IntroScreen.this, First.class);
                startActivity(i);

            }
        });
        signin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(IntroScreen.this, PhoneAuthActivity.class);
                startActivity(i);
            }
        });
    }
}
