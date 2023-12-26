package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager senseMan;

    Sensor lightSensor;
    Sensor proximitySensor;
    Sensor humiditySensor;

    TextView lightTextView;
    TextView proximityTextView;
    TextView humidityTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightTextView = findViewById(R.id.lightTextView);
        proximityTextView = findViewById(R.id.proximityTextView);
        humidityTextView = findViewById(R.id.humidityTextView);

        senseMan = (SensorManager) getSystemService(SENSOR_SERVICE);

        lightSensor = senseMan.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximitySensor = senseMan.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        humiditySensor = senseMan.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        if (lightSensor != null) {
            senseMan.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this, "Light Sensor Found", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Light Sensor not Found", Toast.LENGTH_LONG).show();
        }

        if (proximitySensor != null) {
            senseMan.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this, "Proximity Sensor Found", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Proximity Sensor not Found", Toast.LENGTH_LONG).show();
        }

        if (humiditySensor != null) {
            senseMan.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this, "Humidity Sensor Found", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Humidity Sensor not Found", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_LIGHT:
                lightTextView.setText("Light: " + Float.toString(sensorEvent.values[0]));
                break;
            case Sensor.TYPE_PROXIMITY:
                proximityTextView.setText("Proximity: " + Float.toString(sensorEvent.values[0]));
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                humidityTextView.setText("Humidity: " + Float.toString(sensorEvent.values[0]));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Not needed for this example
    }

    @Override
    protected void onPause() {
        super.onPause();
        senseMan.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        senseMan.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        senseMan.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        senseMan.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
