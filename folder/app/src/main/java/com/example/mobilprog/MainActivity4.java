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

public class MainActivity4 extends AppCompatActivity implements SensorEventListener {


    TextView lepesszamlalotext;
    private SensorManager sensorManager;

    private Sensor sensor;

    int stepcount = 0;

    boolean isSensor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        lepesszamlalotext = findViewById(R.id.Lepesszamlalovaltozo);

        //Elerheto szenzorok lekerese
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager != null) // van elerheto szenzor
        {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }


    public void onSensorChanged(SensorEvent event){
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        double magnitude = Math.sqrt(x*x + y*y + z*z);

        if (magnitude > 3){ //Azért 3, hogy látszódjon a működés, de így folyamatosan számol, 10-nél marad
            stepcount++;
            lepesszamlalotext.setText(String.valueOf(stepcount));
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    protected void onResume(){
        super.onResume();
        if(sensor != null)
        {
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
            isSensor = true;
        }
    }

    public void onPause(){
        super.onPause();
        if(isSensor){
            sensorManager.unregisterListener(this);
            isSensor = false;
        }
    }

}