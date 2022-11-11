package com.example.project2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
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
    TextView basicText, recomText;
    ProgressBar basicBar, recomBar;
    private ImageButton d1,d2, d3, d4, d5, e1, e2, e3, e4, e5, p1, p2, p3, p4, p5;

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

        basicText = findViewById(R.id.basic);
        basicText.setText("기초                                 "+
                "                                         "+BMR2); //기초대사량
        recomText = findViewById(R.id.recommend);
        recomText.setText("권장                                 "+
                "                                         "+diet2); //권장섭취량

        basicBar = findViewById(R.id.basicBar);
        basicBar.setProgress(Integer.parseInt(BMR2));
        recomBar = findViewById(R.id.recomBar);
        recomBar.setProgress(Integer.parseInt(diet2));

        // 링크 걸어주기
        d1 = findViewById(R.id.dietBtn1);
        d2 = findViewById(R.id.dietBtn2);
        d3 = findViewById(R.id.dietBtn3);
        d4 = findViewById(R.id.dietBtn4);
        d5 = findViewById(R.id.dietBtn5);

        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=l1sRulmFDoA"));
                startActivity(intent);
            }
        });

        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=DQrsQiqphkg"));
                startActivity(intent);
            }
        });

        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=uauQBXBUIG8"));
                startActivity(intent);
            }
        });

        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=eRsb26UQ6wM"));
                startActivity(intent);
            }
        });

        d5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=CYcLODSeC-c"));
                startActivity(intent);
            }
        });

        e1 = findViewById(R.id.eatBtn1);
        e2 = findViewById(R.id.eatBtn2);
        e3 = findViewById(R.id.eatBtn3);
        e4 = findViewById(R.id.eatBtn4);
        e5 = findViewById(R.id.eatBtn5);

        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=WHqi90lNeX8&t=147s"));
                startActivity(intent);
            }
        });

        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=w4rkqonvCSA"));
                startActivity(intent);
            }
        });

        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=MWH8g6hCiWM"));
                startActivity(intent);
            }
        });

        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=ApobDUjr7xc"));
                startActivity(intent);
            }
        });

        e5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=aJWMx6NbmO0&t=282s"));
                startActivity(intent);
            }
        });

        p1 = findViewById(R.id.proBtn1);
        p2 = findViewById(R.id.proBtn2);
        p3 = findViewById(R.id.proBtn3);
        p4 = findViewById(R.id.proBtn4);
        p5 = findViewById(R.id.proBtn5);

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=k8KUr8LzaaA"));
                startActivity(intent);
            }
        });

        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=Dj3kYRr5zDo&t=779s"));
                startActivity(intent);
            }
        });

        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=RpZ6RGd0JHU"));
                startActivity(intent);
            }
        });

        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=TulXF8GiReA"));
                startActivity(intent);
            }
        });

        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=AGcu3d1-suw"));
                startActivity(intent);
            }
        });

    }


}