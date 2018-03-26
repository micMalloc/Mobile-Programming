package com.example.ativ.lab2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public Button clearBtn = null;
    public Button printBtn = null;
    public EditText editText = null;
    public TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clearBtn = (Button) findViewById(R.id.clearBtn);
        printBtn = (Button) findViewById(R.id.printBtn);

        editText = (EditText) findViewById(R.id.name_edit_text);
        textView = (TextView) findViewById(R.id.name_text_view);

        //When clearBtn is clicked, the field of text_view and edit_text is clear by using "" empty string
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                editText.setText("");
            }
        });
        //When printBtn is clicked, the string value from edit_text is printed in text_view
        printBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(editText.getText().toString());
            }
        });
    }
}
