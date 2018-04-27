package com.example.ativ.lab3;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public FirstFragment firstFragment;
    public SecondFragment secondFragment;
    public Button btnFrag1 = null;
    public Button btnFrag2 = null;
    public FragmentManager fragmentManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();

        /* Get FragmentManager for Transaction of Fragment */
        fragmentManager = getSupportFragmentManager();

        btnFrag1 = (Button)findViewById(R.id.btnTab1);
        btnFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Replace FirstFragment with SecondFragment */
                fragmentManager.beginTransaction().replace(R.id.fragmentField, firstFragment)
                        .addToBackStack(null).commit();
            }
        });
        btnFrag2 = (Button)findViewById(R.id.btnTab2);
        btnFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Replace Second with FirstFragment */
                fragmentManager.beginTransaction().replace(R.id.fragmentField, secondFragment)
                        .addToBackStack(null).commit();
            }
        });
    }
}
