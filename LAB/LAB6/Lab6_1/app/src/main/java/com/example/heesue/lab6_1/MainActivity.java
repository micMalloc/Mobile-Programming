package com.example.heesue.lab6_1;

import android.Manifest;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String FILEPAHT = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyFile";
    private static final String FILENAME = "lab6_1.txt";
    private File file;
    public EditText editText = null;
    public Button writeBtn, readBtn, clearBtn, finishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        File directory = new File(FILEPAHT);
        directory.mkdir();

        file = new File(directory, FILENAME);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fOut = new FileOutputStream(file);
                    OutputStreamWriter osw = new OutputStreamWriter(fOut);

                    String str = editText.getText().toString();

                    /* Write String to File */
                    osw.write(str);
                    osw.flush();

                    /* Close File Output Stream */
                    osw.close();
                    fOut.close();

                    Toast.makeText(getApplicationContext(), "Done writing SD 'lab6_1.txt'", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(null);
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    /* Open File Input Stream */
                    FileInputStream fIn = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fIn);
                    StringBuffer buf = new StringBuffer();

                    /* Read each character and put into StringBuffer */
                    while (isr.ready()) {
                        buf.append((char) isr.read());
                    }
                    editText.setText(buf.toString());

                    /* Close File Input Stream */
                    isr.close();
                    fIn.close();

                    Toast.makeText(getApplicationContext(), "Done reading SD 'lab6_1.txt'", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Get view component
     */
    void init () {
        getPermission();

        editText = findViewById(R.id.txtData);
        writeBtn = findViewById(R.id.writeBtn);
        readBtn = findViewById(R.id.readBtn);
        clearBtn = findViewById(R.id.clearBtn);
        finishBtn = findViewById(R.id.finishBtn);
    }

    /**
     * get Permission from users for using this app by tedpermission library
     * you must implementation tedpermission in gradle app before using it
     */
    void getPermission () {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(getApplicationContext(), "권한 허가", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(getApplicationContext(), "권한 거부", Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).check();
    }
}