package com.example.heesue.lab6_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView mListView;
    private StudentDatabaseHelper sDatabaseHelper;
    private SQLiteDatabase db;

    private ArrayList<String> mList;
    private ArrayAdapter<String> mAdapter;

    public EditText siField, snField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        siField = findViewById(R.id.si);
        snField = findViewById(R.id.sn);
        findViewById(R.id.addBtn).setOnClickListener(this);
        findViewById(R.id.deleteBtn).setOnClickListener(this);

        mList = new ArrayList<>();
        mListView = findViewById(R.id.mListField);

        // initial invalidate
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(mAdapter);

        sDatabaseHelper = new StudentDatabaseHelper(this);
    }

    public void reset () {
        siField.setText(null);
        snField.setText(null);
    }

    /**
     * Check current input if it exist or not
     * if it exist just exit method
     * if not, insert it into db
     */
    public void insert (String name, String studentID) {

        db = sDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query(
                StudentDatabaseHelper.DATABASE_TABLE,
                null, null, null, null, null, null
        );
        /* Check if current data exist or not */
        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndex(StudentDatabaseHelper.KEY_NAME))
                    .equals(name)) {
                Toast.makeText(getApplicationContext(), "이미 존재하는 이름입니다.", Toast.LENGTH_SHORT).show();
                cursor.close();
                reset();

                return;
            }
        }
        /* There is no redundant, so insert current data into db */
        db = sDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(
                StudentDatabaseHelper.KEY_NAME,
                name
        );
        values.put(
                StudentDatabaseHelper.KEY_ID,
                studentID
        );

        db.insert(
                StudentDatabaseHelper.DATABASE_TABLE,
                null,
                values
        );
    }

    public void delete (String target) {
        db = sDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query(
                StudentDatabaseHelper.DATABASE_TABLE,
                null, null, null, null, null, null
        );

        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndex(StudentDatabaseHelper.KEY_NAME))
                    .equals(target)) {
                db.delete(
                        StudentDatabaseHelper.DATABASE_TABLE,
                        "name=?",
                        new String[]{target}
                );
                Toast.makeText(getApplicationContext(), "해당 항목이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                cursor.close();
                return;
            }
        }
        Toast.makeText(getApplicationContext(),"존재하지 않는 이름입니다.", Toast.LENGTH_SHORT).show();
        reset();
    }

    public void select () {
        db = sDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query(
                StudentDatabaseHelper.DATABASE_TABLE,
                null, null, null, null, null, null
        );

        mList.clear();
        while (cursor.moveToNext()) {
            String aList = cursor.getString(cursor.getColumnIndex(StudentDatabaseHelper.KEY_NAME)) + "  " +
                            cursor.getString(cursor.getColumnIndex(StudentDatabaseHelper.KEY_ID));
            mList.add(aList);
        } cursor.close();
    }

    private void invalidate () {
        select();

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(mAdapter);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addBtn : {
                if (TextUtils.isEmpty(snField.getText()) || TextUtils.isEmpty(siField.getText())) {
                    Toast.makeText(getApplicationContext(), "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    insert(snField.getText().toString(), siField.getText().toString());
                    invalidate();
                } reset();
                break;
            }
            case R.id.deleteBtn : {
                if (TextUtils.isEmpty(snField.getText())) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    delete(snField.getText().toString());
                    invalidate();
                } reset();
                break;
            }
            default: {
                break;
            }
        }
    }

    public class StudentDatabaseHelper extends SQLiteOpenHelper {
        static final String KEY_NAME = "name";
        static final String KEY_ID = "_id";

        static final String DATABASE_NAME = "StudentDB";
        static final String DATABASE_TABLE = "studentInfo";
        static final int DATABASE_VERSION = 1;

        static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + " (" + KEY_NAME + " text primary key not null, " + KEY_ID + " text not null)";

        public StudentDatabaseHelper (Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }
}
