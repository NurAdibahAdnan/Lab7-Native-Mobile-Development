package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SensorManager senseMan;

    ListView lv;

    @SuppressLint({"MissingInflatesId", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.listview);

        senseMan =(SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> sensorList = senseMan.getSensorList(Sensor.TYPE_ALL);

        lv.setAdapter(new ArrayAdapter<Sensor>(this,android.R.layout.simple_list_item_1,sensorList));

    }
}