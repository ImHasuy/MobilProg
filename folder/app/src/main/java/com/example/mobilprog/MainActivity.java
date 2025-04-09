package com.example.mobilprog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

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
        list.add(String.valueOf(myedittext.getText()));
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




}