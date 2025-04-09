package com.example.mobilprog;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {
    EditText act3neve, act3eve;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        act3neve = findViewById(R.id.act3neve);
        act3eve = findViewById(R.id.act3eve);

        sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);

        String savedName = sharedPreferences.getString("name", "");
        String savedAge = sharedPreferences.getString("age", "");

        if (!savedName.isEmpty()) {
            act3neve.setText(savedName);

        }
        if (!savedAge.isEmpty()) {
            act3eve.setText(savedAge);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", act3neve.getText().toString());
        editor.putString("age", act3eve.getText().toString());
        editor.apply();
    }

}

