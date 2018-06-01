package com.example.heesue.lab6_2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public EditText siField, snField;
    public Button readBtn, saveBtn, initBtn;
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("studentID", siField.getText().toString());
                editor.putString("studentName", snField.getText().toString());
                editor.commit();
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siField.setText(settings.getString("studentID", null));
                snField.setText(settings.getString("studentName", null));
            }
        });

        initBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siField.setText(null);
                snField.setText(null);
                editor.clear();
            }
        });
    }

    /**
     * get view Components
     * get editor object
     */
    void init () {
        siField = findViewById(R.id.si);
        snField = findViewById(R.id.sn);

        readBtn = findViewById(R.id.readBtn);
        saveBtn = findViewById(R.id.saveBtn);
        initBtn = findViewById(R.id.initBtn);

        settings = getSharedPreferences("lab6_2", MODE_PRIVATE);
        editor = settings.edit();
    }
}
