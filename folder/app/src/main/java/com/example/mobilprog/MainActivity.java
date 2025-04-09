package com.example.mobilprog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> list;
    ArrayAdapter<String> adapter;
    EditText myedittext;

    TextView Nevvaltozo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Nevvaltozo = findViewById(R.id.ValtoztatandoNevId);

        SharedPreferences sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);
        String savedName = sharedPreferences.getString("name", "");
        String savedAge = sharedPreferences.getString("age", "");

        if (!savedName.isEmpty()) {
            Nevvaltozo.setText(savedName);
        }else{
            Nevvaltozo.setText("Anonymous"); // Ha nincs mentett akkor ez lesz
        }

        if (!savedAge.isEmpty()) {
            Toast.makeText(this, savedAge + " éves vagy", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Kérlek válassz a menüpontokból!", Toast.LENGTH_SHORT).show();
        }



        String[] array = {"0.5 km", "1 km", "3 km", "5 km", "10 km"};
        list = new ArrayList<>(Arrays.asList(array));
        ListView listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        myedittext = findViewById(R.id.ListaEditTextje);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long id) {
                String selectedItem = list.get(position);
                myedittext.setText(selectedItem);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View
                    view, final int position, long id) {
                String selectedItem = list.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirmation")
                        .setMessage("Biztos ki akarod törölni az alábbi elemet: " + selectedItem)
                        .setPositiveButton("Igen", new
                                DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                list.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Nem", new
                                DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int
                                    id) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                        return true;
            }
        });


    }

    public void hozzaadas(View view) {
        myedittext = findViewById(R.id.ListaEditTextje);
        list.add(String.valueOf(myedittext.getText() + " km"));
        adapter.notifyDataSetChanged();
    }


    public void torles(View view) {
        myedittext = findViewById(R.id.ListaEditTextje);
        String itemToRemove = String.valueOf(myedittext.getText());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(itemToRemove)) {
                list.remove(i);
                adapter.notifyDataSetChanged();
                break;
            }
        }
    }

    public void utolsotorles(View view) {
        if (list.size() > 0) {
            list.remove(list.size() - 1);
            adapter.notifyDataSetChanged();
        }
    }

    public void Fenymerore(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    public void hozzaadasra(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
        startActivity(intent);
    }

    public void lepesre(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity4.class);
        startActivity(intent);
    }
}