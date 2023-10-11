package com.example.pr18fully;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> dataList;

    private static final String ATTRIBUTE_NAME_TEXT = "text";
    private static final String ATTRIBUTE_NAME_IMAGE = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button1);
        listView = findViewById(R.id.listView);



        // Создаем массив данных для списка
        dataList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put(ATTRIBUTE_NAME_TEXT, "Item " + i);
            map.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher_foreground);
            dataList.add(map);
        }

        // Определяем массивы "from" и "to" для сопоставления данных и View-компонентов
        String[] from = {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE};
        int[] to = {R.id.textView, R.id.imageView};

        // Создаем адаптер SimpleAdapter
        adapter = new SimpleAdapter(this, dataList, R.layout.list_item, from, to);

        // Привязываем адаптер к списку
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Обработка клика на элементе списка
                CheckBox checkBox = view.findViewById(R.id.checkBox);
                TextView textView = view.findViewById(R.id.textView);
                if (checkBox.isChecked()) {
                    checkBox.setChecked(false);
                    Toast.makeText(MainActivity.this, "Unchecked: " + textView.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    checkBox.setChecked(true);
                    Toast.makeText(MainActivity.this, "Checked: " + textView.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Установка обработчика для кнопки
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создаем интент для перехода на новую активность
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}