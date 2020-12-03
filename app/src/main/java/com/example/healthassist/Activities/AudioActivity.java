package com.example.healthassist.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.healthassist.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.ShortBuffer;

public class AudioActivity extends AppCompatActivity {

    Button playBtn, pauseBtn, recordButton, stopButton;
    private static final String LOG_TAG = "AudioRecordTest";
    /*
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String fileName = null;

    private MediaRecorder recorder = null;
    private MediaPlayer player = null;
    private boolean isRecording = false;
    private boolean isPlaying = false;
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};
    */
    final int SAMPLING_RATE=44100;

    boolean mContinue=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        initializeViews();

        /*
        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/audiorecordtest.3gp";

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);



         */

    }

    private void initializeViews() {
        playBtn = findViewById(R.id.play);
        recordButton = findViewById(R.id.record);
        stopButton = findViewById(R.id.stop);
        pauseBtn = findViewById(R.id.pause);

    }

    public void record(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(!mContinue){
                    mContinue=true;
                }
                android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_AUDIO);
                int bufferSize=AudioRecord.getMinBufferSize(SAMPLING_RATE,AudioFormat.CHANNEL_IN_MONO,
                        AudioFormat.ENCODING_PCM_16BIT);

                if(bufferSize==AudioRecord.ERROR || bufferSize==AudioRecord.ERROR_BAD_VALUE){
                    bufferSize=SAMPLING_RATE*2;
                }

                short[] audioBuffer=new short[bufferSize/2];

                AudioRecord record=new AudioRecord(MediaRecorder.AudioSource.MIC,
                        SAMPLING_RATE,AudioFormat.CHANNEL_IN_MONO,AudioFormat.ENCODING_PCM_16BIT,
                        bufferSize);
                if(record.getState()!=AudioRecord.STATE_INITIALIZED){
                    Log.e(LOG_TAG, "run: "+"Error in Recording" );
                    return;
                }
                record.startRecording();
                Log.e(LOG_TAG, "run: RECORDING" );
                long shortsRead=0;
                //File f=new File(Environment.getExternalStorageDirectory().getPath(),"Recording.txt");

                OutputStreamWriter writer=null;
                try {
                    writer=new OutputStreamWriter(openFileOutput("config.txt", Context.MODE_PRIVATE));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                StringBuilder builder=new StringBuilder();
                while(mContinue){
                    int noOfShorts=record.read(audioBuffer,0,audioBuffer.length);
                    shortsRead+=noOfShorts;
                    if(writer!=null){
                        for(short s:audioBuffer){
                            builder.append(String.valueOf(s)).append(" ");

                        }

                        Log.e(LOG_TAG, "run: "+audioBuffer.length );

                    }
                }
                try {
                    writer.append(builder.toString());
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }

                record.stop();
                record.release();
                Log.e(LOG_TAG, "stop:" );

            }
        }).start();

    }
    public void play(View view){

    }
    public void pause(View view){

    }
    public void stop(View view){
        if(mContinue){
            mContinue=false;
        }
    }

/*
    public void play(View view) {
        if (!isPlaying) {
            isPlaying=true;
            playBtn.setText("PLAY");
            startPlaying();
        } else {
            isPlaying=false;
            playBtn.setText("PLAYING");
            stopPlaying();
        }

    }

    public void pause(View view) {

    }

    public void stop(View view) {

    }

    public void record(View view) {
        if (!isRecording) {
            isRecording=true;
            recordButton.setText("RECORDING");
            startRecording();
        } else {
            isRecording=false;
            recordButton.setText("RECORD");
            stopRecording();
        }

    }

    private void startPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(fileName);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "startPlaying: " + "PLAYING FAILED");
        }
    }

    private void stopPlaying() {
        player.release();
        player = null;
    }

    private void startRecording() {
        recorder = new MediaRecorder();
//        String prop=AudioManager.getProperty(AudioManager.PROPERTY_SUPPORT_AUDIO_SOURCE_UNPROCESSED);
        recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setAudioSamplingRate(SAMPLING_RATE);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recorder.start();
        Log.e(LOG_TAG, "startRecording: "+"SRAER" );
    }

    private void stopRecording() {

        recorder.stop();
        recorder.release();
        recorder = null;
    }
    @Override
    public void onStop() {
        super.onStop();
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }

        if (player != null) {
            player.release();
            player = null;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();

    }

 */
}
