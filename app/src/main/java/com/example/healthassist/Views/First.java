package com.example.healthassist.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthassist.R;

public class First extends AppCompatActivity {

    public ImageButton Meas;
    public Button acc;
    public EditText ed1, ed2;
    private Toast mainToast;
    public static String passStr, usrStr, checkpassStr, usrStrlow;
    UserDB check = new UserDB(this);
    CheckBox chkRememberMe;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        getSupportActionBar().hide();
        Meas = findViewById(R.id.prime);
        acc = findViewById(R.id.newacc);
        ed1 = findViewById(R.id.edtu1);
        ed2 = findViewById(R.id.edtp1);
        chkRememberMe = findViewById(R.id.checkBoxRemember);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);


        if (saveLogin) {
            ed1.setText(loginPreferences.getString("username", ""));
            ed2.setText(loginPreferences.getString("password", ""));
            chkRememberMe.setChecked(true);
        }

        Meas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usrStrlow = ed1.getText().toString();
                passStr = ed2.getText().toString();
                usrStr = usrStrlow.toLowerCase();


                if (usrStr.length() < 3 || usrStr.length() > 20) {
                    mainToast = Toast.makeText(First.this.getApplicationContext(), "Username length must be between 3-20 characters", Toast.LENGTH_SHORT);
                    mainToast.show();
                }

                if (passStr.length() < 3 || passStr.length() > 20) {
                    mainToast = Toast.makeText(First.this.getApplicationContext(), "Password length must be between 3-20 characters", Toast.LENGTH_SHORT);
                    mainToast.show();
                } else if (passStr.isEmpty() || usrStr.isEmpty()) {
                    mainToast = Toast.makeText(First.this.getApplicationContext(), "Please enter your Username and Password ", Toast.LENGTH_SHORT);
                    mainToast.show();
                } else {
                    checkpassStr = check.checkPass(usrStr);
                    if (passStr.equals(checkpassStr)) {
                        if (chkRememberMe.isChecked()) {
                            loginPrefsEditor.putBoolean("saveLogin", true);
                            loginPrefsEditor.putString("username", usrStr);
                            loginPrefsEditor.putString("password", passStr);
                            loginPrefsEditor.apply();
                        } else {
                            loginPrefsEditor.clear();
                            loginPrefsEditor.commit();
                        }

                        Intent i = new Intent(v.getContext(), Primary.class);
                        i.putExtra("Usr", usrStr);
                        First.this.startActivity(i);
                        First.this.finish();

                    } else {
                        //Toast something
                        mainToast = Toast.makeText(First.this.getApplicationContext(), "Username/Password is incorrect", Toast.LENGTH_SHORT);
                        mainToast.show();
                    }
                }

            }
        });

        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Login.class);
                First.this.startActivity(i);
                First.this.finish();
            }
        });
    }


}
