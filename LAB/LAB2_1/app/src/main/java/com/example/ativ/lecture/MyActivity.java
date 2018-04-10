package com.example.ativ.lecture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {
    Button comebackBtn = null;
    Intent data = null;
    String name, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        /**
         * Get the passed data(String) from the previous activity
         * If the passed data exist, get two string data (name and age)
         * Then, float Them by using Toast
         */
        data = getIntent();
        if (data != null) {
            name = data.getStringExtra("name");
            age = data.getStringExtra("age");

            Toast.makeText(this, "Student INFO: " + name + ", " + age, Toast.LENGTH_LONG).show();
        }
        /* Get Button(view) component by ID */
        comebackBtn = (Button)findViewById(R.id.btnComeBack);
        /**
         * When comeback button is clicked, Current Activity is finished by using finsh() method
         */
        comebackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
