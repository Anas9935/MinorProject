package com.example.healthassist.Views;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.healthassist.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReportActivity extends AppCompatActivity {

    TextView name,age,gender,time,vitalName,vitalVal,saveBtn,sendBtn;
    EditText addnl;
    private int REQUEST_CODE_PERMISSIONS = 1001;
    Uri uri=null;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
       // this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
       // getSupportActionBar().setDisplayShowCustomEnabled(true);
        //getSupportActionBar().setCustomView(R.layout.custom_app_bar);
        name=findViewById(R.id.Report_name);
        age=findViewById(R.id.Report_age);
        gender=findViewById(R.id.Report_gender);
        time=findViewById(R.id.Report_datentime);
        vitalName=findViewById(R.id.Report_vitalName);
        vitalVal=findViewById(R.id.Report_vital_val);
        saveBtn=findViewById(R.id.Report_saveBtn);
        sendBtn=findViewById(R.id.Report_sendBtn);
        addnl=findViewById(R.id.Report_addnl);

        UserDB data=new UserDB(this);
        String user=null;
        //Bundle extras = getIntent().getExtras();
        Intent intent=getIntent();
        if (intent != null) {
            user = intent.getStringExtra("Usr");
            //The key argument here must match that used in the other activity
        }

        int Age = Integer.parseInt(data.getage(user));
        int gen = Integer.parseInt(data.getgender(user));
        final int vitalType=intent.getIntExtra("vitalType",0);
        String vitalValue=intent.getStringExtra("vitalVal");
        if(user!=null){
            name.setText(user);
            age.setText(String.valueOf(Age));
            switch (gen){
                case 1:{
                    gender.setText("Male");
                    break;
                }
                case 2:{
                    gender.setText("Female");
                    break;
                }
            }
            switch (vitalType){
                case 1:{
                    vitalName.setText("Heart Rate");
                    vitalVal.setText(vitalValue);
                    break;

                }
                case 2:{
                    vitalName.setText("Blood Pressure");
                    vitalVal.setText(vitalValue);
                    break;

                }
                case 3:{
                    vitalName.setText("Respiration Rate");
                    vitalVal.setText(vitalValue);
                    break;

                }
            }
            long tm=System.currentTimeMillis();
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MMM/yyyy HH:MM");
            time.setText(sdf.format(new Date(tm)));

        }
        else{
            name.setText("Unknown");
            age.setText("Unknown");
            gender.setText("Unknown");
            switch (vitalType){
                case 1:{
                    vitalName.setText("Heart Rate");
                    vitalVal.setText("Unknown");
                    break;

                }
                case 2:{
                    vitalName.setText("Blood Pressure");
                    vitalVal.setText("Unknown");
                    break;

                }
                case 3:{
                    vitalName.setText("Respiration Rate");
                    vitalVal.setText("Unknown");
                    break;

                }
            }
            long tm=System.currentTimeMillis();
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MMM/yyyy HH:MM");
            time.setText(sdf.format(new Date(tm)));

        }


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addnl.getText()==null || addnl.getText().equals("")){
                    addnl.setText("None");
                }
                saveBtn.setVisibility(View.INVISIBLE);
                sendBtn.setVisibility(View.INVISIBLE);

                View root=getWindow().getDecorView().getRootView();
                Bitmap snapshot=takeScreenShot(root);
                Log.e("REPORT", "onClick: "+"Snapshot taken" );
                String fName="";
                switch (vitalType){
                    case 1:{
                        fName="HR_"+System.currentTimeMillis();
                        break;
                    }
                    case 2:{
                        fName="BP_"+System.currentTimeMillis();
                        break;
                    }
                    case 3:{
                        fName="RR_"+System.currentTimeMillis();
                        break;
                    }
                }
                Uri u=saveImage(ReportActivity.this,snapshot,fName);
                if(u!=null)
                    Log.e("REport", "onClick: "+u.getPath() );
                    Toast.makeText(ReportActivity.this, "Report Saved at"+u.getPath(), Toast.LENGTH_SHORT).show();
                finish();


            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addnl.getText()==null || addnl.getText().equals("")){
                    addnl.setText("None");
                }
//               saveBtn.setVisibility(View.INVISIBLE);
//                sendBtn.setVisibility(View.INVISIBLE);
                View root=getWindow().getDecorView().getRootView();
                Bitmap snapshot=takeScreenShot(root);
                Log.e("REPORT", "onClick: "+"Snapshot taken" );
                String fName="";
                switch (vitalType){
                    case 1:{
                        fName="HR_"+System.currentTimeMillis();
                        break;
                    }
                    case 2:{
                        fName="BP_"+System.currentTimeMillis();
                        break;
                    }
                    case 3:{
                        fName="RR_"+System.currentTimeMillis();
                        break;
                    }
                }
                Uri u=saveImage(ReportActivity.this,snapshot,fName);
                Log.d("uri",u+"");
                if(allPermissionsGranted())
                {
                    sendTo(u);
                }
                else
                {
                    uri=u;
                    ActivityCompat.requestPermissions(ReportActivity.this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
                }


               // finish();




            }
        });

    }
    private void sendTo(Uri uri)
    {
        ImageView imageView=findViewById(R.id.imageView);
        Glide.with(this).load(uri).into(imageView);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
       // startActivity(Intent.createChooser(intent, "Share Image Via"));
    }
    private boolean allPermissionsGranted()
    {

        for (String permission : REQUIRED_PERMISSIONS)
        {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted())
            {
                 sendTo(uri);

            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public Bitmap takeScreenShot(View view) {
        // configuramos para que la view almacene la cache en una imagen
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        view.buildDrawingCache();

        if(view.getDrawingCache() == null) return null; // Verificamos antes de que no sea null

        // utilizamos esa cache, para crear el bitmap que tendra la imagen de la view actual
        Bitmap snapshot = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();

        return snapshot;
    }



    public  Uri saveImage(Context context, Bitmap img, String name)
    {

        File f=null;
         f=saveimagefile();
//        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.Q){
//
//                String path= Environment.getExternalStorageDirectory().getPath()+"/HealthAssist/"+name+".jpg";
// //               dir=new File(Environment.getExternalStorageDirectory().getPath()+"/CamScan/.Original");
//                f=new File(path);
//
//
//            //          String path2=Environment.getExternalStorageDirectory().getPath()+System.currentTimeMillis()+".jpg";
////            original=new File(path2);
//        }else{
//            f=new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),"HealthAssist/"+name+".jpg");
//
//
//        }
//
//        File dir=new File(Environment.getExternalStorageDirectory().getPath()+"/HealthAssist");
//        if(!dir.exists() && !dir.isDirectory()){
//            dir.mkdirs();
//        }

        try {

            FileOutputStream fos=new FileOutputStream(f,false);
            img.compress(Bitmap.CompressFormat.JPEG,100,fos);

            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Uri.fromFile(f);
    }
    private File  saveimagefile()
    {
        File dir;
        File f=null;
        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.Q){
            String path= Environment.getExternalStorageDirectory().getPath()+"/HealthAssist/.Original/"+System.currentTimeMillis()+".jpg";
            dir=new File(Environment.getExternalStorageDirectory().getPath()+"/HealthAssist/.Original");
            f=new File(path);
        }
        else{
            f=new File(ReportActivity.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES),"HealthAssist/.Original/"+System.currentTimeMillis()+".jpg");
            dir=new File(ReportActivity.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES),"HealthAssist/.Original");
        }
        if(!dir.exists() && !dir.isDirectory()){
            dir.mkdirs();
        }
        return  f;
    }

}
