package com.example.heesue.temp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText input_area = null;
    private Button calculate_btn = null;
    private TextView progress_view = null;
    private TextView result_view = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress_view = findViewById(R.id.output_area);
        result_view = findViewById(R.id.result_area);
        input_area = findViewById(R.id.input_receiver);

        calculate_btn = findViewById(R.id.calculate_btn);
        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int data;

                /**
                 * If there is no input in EditText field
                 * Toast the message to user for typing the input
                 * If there is input in EditText field
                 * Create and execute FactorialCalculator class for doing factorial task
                 */
                if (!TextUtils.isEmpty(input_area.getText())) {
                    data = Integer.parseInt(input_area.getText().toString());
                    new FactorialCalculator(data).execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Type the Input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class FactorialCalculator extends AsyncTask<Void, Integer, Void> {

        private String progress = "";
        private int input;
        private long result = 1;

        public FactorialCalculator (int data) {
            input = data;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            result_view.setText("= ?");
        }

        @Override
        protected Void doInBackground(Void... voids) {

            for (int i = input; i > 0; -- i) {
                try {
                    Thread.sleep(500);

                    result *= i;
                    publishProgress(i);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            progress += (Integer.toString(values[0].intValue()) + " ");
            progress_view.setText(progress);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            result_view.setText("= " + Long.toString(result));
            input_area.setText("");
        }
    }
}
