package com.example.ativ.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText urlText = null;
    Button nextBtn = null;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Get EditText and Button Component from the layout
         * Make the call back method to treat button click event
         * When 'NEXT' button is clicked,
         * First, get the url string from the EditText View and Put it into the intent
         * Second, start MyActivity via Intent
         */
        urlText = (EditText)findViewById(R.id.urlText);
        nextBtn = (Button)findViewById(R.id.btnNext);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, MyActivity.class);
                intent.putExtra("url", urlText.getText().toString());
                startActivity(intent);
            }
        });
    }
}
