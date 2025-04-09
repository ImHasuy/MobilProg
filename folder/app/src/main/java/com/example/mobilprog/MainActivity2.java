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
import android.graphics.Color;
import android.view.View;



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
            float lightValue = event.values[0];
            fenyerzekelovaltozo.setText(String.valueOf(lightValue));
            changeBackgroundColor(lightValue);
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
            sensorManager.unregisterListener(this);
            isSensor = false;
        }
    }


    private void changeBackgroundColor(float value) {
        View rootView = findViewById(R.id.main);


        float max = 30000f;

        float ratio = Math.min(1f, value / max);

        int red = (int) (0 + (100 - 0) * ratio);
        int green = (int) (0 + (100 - 0) * ratio);
        int blue = (int) (50 + (205 - 50) * ratio);

        int color = Color.rgb(red, green, blue);
        rootView.setBackgroundColor(color);
    }






}