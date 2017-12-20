package com.example.ativ.myalam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RamdomCalculActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ramdom_calcul);
    }

}

/*
*
* package com.example.jjw920418.random_calculation_problem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tV;
    TextView tV2;
    TextView tV3;
    EditText eT;
    int result = 0;
    String input;

    @Override
    protected void onPause() {
        super.onPause();
        R_d();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tV = (TextView) findViewById(R.id.tV);
        tV2 = (TextView) findViewById(R.id.tV2);
        tV3 = (TextView) findViewById(R.id.tV3);
        eT = (EditText) findViewById(R.id.eT);
        R_d();

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = eT.getText().toString();
                if (String.valueOf(result).equals(input))
                    startActivity(new Intent(MainActivity.this, O.class));
                else
                    startActivity(new Intent(MainActivity.this, X.class));


            }
        });
    }

    public void R_d() {
        Random random = new Random();
        int a = (random.nextInt(100));
        int b = (random.nextInt(100));
        tV.setText(String.valueOf(a));
        tV3.setText(String.valueOf(b));


        if (a >= b) {
            int operator = random.nextInt(3);
            switch (operator) {
                case 0:
                    tV2.setText("+");
                    result = a + b;
                    break;
                case 1:
                    tV2.setText("-");
                    result = a - b;
                    break;
                case 2:
                    tV2.setText("*");
                    result = a * b;
                    break;
            }
        } else {
            int operator = random.nextInt(2);
            switch (operator) {
                case 0:
                    tV2.setText("+");
                    result = a + b;
                    break;

                case 1:
                    tV2.setText("*");
                    result = a * b;
                    break;
            }
        }

    }
}

* */
