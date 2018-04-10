package com.example.ativ.lab2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {
    TextView uriTextView = null;
    Button goBtn = null;
    Button backBtn = null;
    Intent data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        data = getIntent();
        final String url = data.getStringExtra("url");

        uriTextView = (TextView) findViewById(R.id.uriView);
        uriTextView.setText(url);
        /**
         * Get the GO Button component from Layout
         * Make the call back method to treat button click event
         * When 'GO' button is clicked, check if url exist or not
         * if it exist, go website
         * if not, float Toast message
         */
        goBtn = (Button)findViewById(R.id.goBtn);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(url)) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + url)));
                } else {
                    Toast.makeText(MyActivity.this, "주소를다시입력하세요", Toast.LENGTH_LONG).show();
                }
            }
        });
        /**
         * Get the BACK button component from the layout
         * Make the call back method to treat button click event
         * When 'BACK' button is clicked,
         * First, destroy MyActivity, comeback the MainActivity
         * Second, float Toast Message
         */
        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(getApplicationContext(), "뒤로가기버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
