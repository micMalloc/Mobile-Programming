package com.example.ativ.lab1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btn);
        registerForContextMenu(btn);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Button Menu");
        menu.add(0, 1, 100, "Red");
        menu.add(0, 2, 100, "Green");
        menu.add(0, 3, 100, "Blue");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1: {
                btn.setTextColor(Color.RED);
                break;
            }
            case 2: {
                btn.setTextColor(Color.GREEN);
                break;
            }
            case 3: {
                btn.setTextColor(Color.BLUE);
                break;
            }
        } return super.onContextItemSelected(item);
    }
}
