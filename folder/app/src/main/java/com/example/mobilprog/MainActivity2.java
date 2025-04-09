package com.example.mobilprog;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity2 extends AppCompatActivity implements SensorEventListener {
    TextView fenyerzekelovaltozo;
    private SensorManager sensorManager;

    private Sensor LightSensor;
    Boolean isSensor = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager != null)
        {
            LightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        fenyerzekelovaltozo = findViewById(R.id.fenyszenzorertek);
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            fenyerzekelovaltozo.setText(String.valueOf(event.values[0]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onResume(){
        super.onResume();
        if(LightSensor != null){
            sensorManager.registerListener(this,LightSensor,SensorManager.SENSOR_DELAY_NORMAL);
            isSensor = true;
        }
    }

    protected void onPause(){
        super.onPause();
        if(isSensor){
            sensorManager.unregisterListener(this); //leallitjuk a listenert
            isSensor = false;
        }
    }


}