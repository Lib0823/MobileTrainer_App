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

    int version = 1;
    DatabaseOpenHelper helper;
    SQLiteDatabase database;

    String sql;
    Cursor cursor;

    Double BMR, diet;
    TextView bmrText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        //DataBase연결부분
        helper = new DatabaseOpenHelper(DietActivity.this, DatabaseOpenHelper.tableName, null, version);
        database = helper.getWritableDatabase();

        sql = "SELECT * FROM "+ helper.tableName + " WHERE login = '1'";
        cursor = database.rawQuery(sql, null);

        cursor.moveToNext();   // 첫번째에서 다음 레코드가 없을때까지 읽음
        String age = cursor.getString(3);   //키
        String height = cursor.getString(4);   //키
        String weight = cursor.getString(5);   //몸무게
        String gender = cursor.getString(6);   //성별
        int weight2 = Integer.parseInt(weight);
        int height2 = Integer.parseInt(height);
        int age2 = Integer.parseInt(age);

        if(gender.equals("man")){
            BMR = (13.397*weight2) + (4.799*height2) - (5.677*age2) + 88.362;
        }else if(gender.equals("woman")){
            BMR =  (9.247*weight2) + (3.098*height2) - (4.330*age2) + 447.593;
        }

        diet = (BMR*1.4) * 0.76;

        String diet2 = String.format("%.0f", diet);
        String BMR2 = String.format("%.0f", BMR);

        bmrText = findViewById(R.id.BMR);
        bmrText.setText("기초대사량 : "+BMR2+"\n"+diet2+"칼로리 섭취 추천"); //기초대사량으로 일일섭취 칼로리 추천

    }


    }