package com.example.ativ.lecturereview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        TextView nameView = (TextView) findViewById(R.id.inputName);
        TextView genderView = (TextView) findViewById(R.id.inputGender);
        TextView methodView = (TextView) findViewById(R.id.inputMethod);

        /* Set the widget with received data from MainActivity */
        Intent data = getIntent();
        nameView.setText(data.getStringExtra("name"));
        genderView.setText(data.getStringExtra("gender"));
        methodView.setText(data.getStringExtra("method"));

        /* Implement Listener */
        Button backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
