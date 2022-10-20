package com.example.project2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DietActivity extends AppCompatActivity {

    String field = "diet";

    int version = 1;
    DatabaseOpenHelper helper;
    SQLiteDatabase database;

    String sql;
    Cursor cursor;

    String content;

    Button boardBtn;
    EditText boardInput;
    TextView board;

    ArrayList<String> list = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        Intent getId = getIntent(); //전달할 데이터를 받을 Intent
        //text 키값으로 데이터를 받는다. String을 받아야 하므로 getStringExtra()를 사용함
        String id = getId.getStringExtra("text");
        //DataBase연결부분
        helper = new DatabaseOpenHelper(DietActivity.this, DatabaseOpenHelper.tableName2, null, version);
        database = helper.getWritableDatabase();

        boardInput = findViewById(R.id.boardInput);

        board = findViewById(R.id.board);

        boardBtn = findViewById(R.id.boardBtn);
        boardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content = boardInput.getText().toString();
                helper.insertBoard(database,id, field, content);
                sql = "SELECT content FROM "+ helper.tableName2 + " WHERE field = '" + field + "'";
                cursor = database.rawQuery(sql, null);


            }
        });
    }






    }