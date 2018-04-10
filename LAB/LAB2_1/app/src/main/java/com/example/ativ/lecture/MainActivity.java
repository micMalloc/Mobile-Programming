package com.example.ativ.lecture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name = null;
    EditText age = null;
    Button btnNext = null;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_main layout inflation
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.inputName);
        age = (EditText)findViewById(R.id.inputAge);

        btnNext = (Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Create a Intent */
                intent = new Intent(getApplicationContext(), MyActivity.class);
                /**
                 * Get the strings from two editText field which are name and age strings
                 * Then, Put them in the Intent
                 */
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("age", age.getText().toString());
                /* Start MyActivity by using Intent */
                startActivity(intent);
            }
        });
    }
}
