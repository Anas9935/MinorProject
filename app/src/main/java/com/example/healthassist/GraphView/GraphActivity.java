package com.example.healthassist.GraphView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.healthassist.R;
import com.example.healthassist.Views.Primary;
import com.example.healthassist.Views.ProfileActivity;
import com.example.healthassist.Views.ReportActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
public class GraphActivity extends AppCompatActivity {

    private static final Random rand = new Random();
    private LineGraphSeries<DataPoint> seriesHR;
    private LineGraphSeries<DataPoint> seriesBP;
    private LineGraphSeries<DataPoint> seriesRR;
    private int lastX = 1;
    String days[]={"Mn","Tu","Wn","Th","Fr","St","Sn"};
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
      //  getSupportActionBar().hide();
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_app_bar);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        BottomNavigationBar();
        heart_rate_graph();
        blood_pressure();
        respiration_rate();
    }
    public void heart_rate_graph()
    {
        final GraphView graph = (GraphView) findViewById(R.id.graph_heart_rate);

        //Create the Datapoins
        seriesHR = new LineGraphSeries<DataPoint>();
        // customize the viewport
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setXAxisBoundsManual(true);
        viewport.setMinY(60);
        viewport.setMaxY(120);
        viewport.setMinX(1);
        viewport.setMaxX(7);
        viewport.setScrollable(false);
        viewport.setBackgroundColor(Color.parseColor("#000000"));
        for(int i=0;i<10;i++)
          addEntryHR();
        //series.setAnimated(true);
        seriesHR.setTitle("Heart Rate");
        seriesHR.setColor(Color.parseColor("#ffff00"));
        seriesHR.setDataPointsRadius(7);
        graph.getGridLabelRenderer().setGridColor(Color.parseColor("#FFFFFF"));
        StaticLabelsFormatter staticLabelsFormattery=new StaticLabelsFormatter(graph);
        staticLabelsFormattery.setVerticalLabels(new String[]{"60","70","80","90","100","110","120"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormattery);

        StaticLabelsFormatter staticLabelsFormatter=new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(days);
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graph.addSeries(seriesHR);
    }
    private void addEntryHR() {
        seriesHR.appendData(new DataPoint(lastX++,60+ rand.nextDouble() * 40d), true, 10);
    }
    public void blood_pressure()
    {
        final GraphView graph = (GraphView) findViewById(R.id.graph_blood_pressure);
        lastX = 1;
        //Create the Datapoins
        seriesBP = new LineGraphSeries<DataPoint>();
        // customize the viewport
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setXAxisBoundsManual(true);
        viewport.setMinY(80);
        viewport.setMaxY(200);
        viewport.setMinX(1);
        viewport.setMaxX(7);
        viewport.setScrollable(false);
        viewport.setBackgroundColor(Color.parseColor("#000000"));
        for(int i=0;i<10;i++)
            addEntryBP();
        //series.setAnimated(true);
        seriesBP.setTitle("Blood Pressure");
        seriesBP.setColor(Color.parseColor("#ffff00"));
        seriesBP.setDataPointsRadius(7);
        graph.getGridLabelRenderer().setGridColor(Color.parseColor("#FFFFFF"));
        StaticLabelsFormatter staticLabelsFormattery=new StaticLabelsFormatter(graph);
        staticLabelsFormattery.setVerticalLabels(new String[]{"80","100","120","140","160","180","200"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormattery);

        StaticLabelsFormatter staticLabelsFormatter=new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(days);
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graph.addSeries(seriesBP);
    }
    private void addEntryBP() {
        seriesBP.appendData(new DataPoint(lastX++,80+ rand.nextDouble() * 40d), true, 10);
    }
    public void respiration_rate()
    {
        final GraphView graph = (GraphView) findViewById(R.id.graph_respiration_rate);
        lastX = 1;
        //Create the Datapoins
        seriesRR = new LineGraphSeries<DataPoint>();
        // customize the viewport
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setXAxisBoundsManual(true);
        viewport.setMinY(40);
        viewport.setMaxY(100);
        viewport.setMinX(1);
        viewport.setMaxX(7);
        viewport.setScrollable(false);
        viewport.setBackgroundColor(Color.parseColor("#000000"));
        for(int i=0;i<10;i++)
            addEntryRR();
        //series.setAnimated(true);
        seriesRR.setTitle("Respiration Rate");
        seriesRR.setColor(Color.parseColor("#ffff00"));
        seriesRR.setDataPointsRadius(7);
        graph.getGridLabelRenderer().setGridColor(Color.parseColor("#FFFFFF"));
        StaticLabelsFormatter staticLabelsFormattery=new StaticLabelsFormatter(graph);
        staticLabelsFormattery.setVerticalLabels(new String[]{"40","50","60","70","80","90","100"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormattery);
        StaticLabelsFormatter staticLabelsFormatter=new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(days);
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graph.addSeries(seriesRR);
    }
    private void addEntryRR()
    {
        seriesRR.appendData(new DataPoint(lastX++,40+ rand.nextDouble() * 40d), true, 10);
    }
    private void BottomNavigationBar()
    {
        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                View view = findViewById(R.id.bottom_navigation);
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        break;
                    case R.id.action_test:
                        intent = new Intent(view.getContext(), Primary.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        break;
                    case R.id.action_profile :
                        intent = new Intent(view.getContext(), ProfileActivity.class);
//                       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
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