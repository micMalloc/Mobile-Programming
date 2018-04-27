package com.example.ativ.lecturereview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    public Button registerBtn = null;
    public EditText nameField = null;
    public CheckBox cb1, cb2;
    public RadioButton rb1, rb2;
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = (EditText) findViewById(R.id.nameField);
        rb1 = (RadioButton) findViewById(R.id.manBox);
        rb2 = (RadioButton) findViewById(R.id.womanBox);
        cb1 = (CheckBox) findViewById(R.id.smsBox);
        cb2 = (CheckBox) findViewById(R.id.eMailBox);

        registerBtn = (Button) findViewById(R.id.registBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), NewActivity.class);
                setData();
                startActivity(intent);
            }
        });
    }

    /**
     * Set required data into the Intent
     * Get the data from widgets
     * Put them into the intent to be used
     */
    public void setData() {
        intent.putExtra("name", nameField.getText().toString());

        if (rb1.isChecked()) {
            intent.putExtra("gender", rb1.getText().toString());
        } else {
            intent.putExtra("gender", rb2.getText().toString());
        }

        String method = "";
        if (cb1.isChecked()) {
            method += cb1.getText().toString();
        }
        if (cb2.isChecked()) {
            if (!TextUtils.isEmpty(method)) {
                method += "  &  ";
            }
            method += cb2.getText().toString();
        }
        intent.putExtra("method", method);
    }

    /**
     * Overriding the onRestart method when called when MainActivity is on foreground again
     * Reset EditText Field, CheckBox and RadioBox
     */
    @Override
    protected void onRestart() {
        nameField.setText("");

        if (rb1.isChecked()) {
            rb1.setChecked(false);
        } else {
            rb2.setChecked(false);
        }

        if (cb1.isChecked()) {
            cb1.setChecked(false);
        }
        if (cb2.isChecked()) {
            cb2.setChecked(false);
        }

        super.onRestart();
    }
}
