package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LeanmassActivity extends AppCompatActivity {

    int version = 1;
    DatabaseOpenHelper helper;
    SQLiteDatabase database;

    String sql;
    Cursor cursor;

    Double BMR, leanmass;
    TextView basicText, recomText;
    ProgressBar basicBar, recomBar;

    private ImageButton lf1, lf2, lf3, lf4, lf5;
    private ImageButton ls1, ls2, ls3, ls4, ls5;
    private ImageButton lb1, lb2, lb3, lb4, lb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leanmass);

        //DataBase연결부분
        helper = new DatabaseOpenHelper(LeanmassActivity.this, DatabaseOpenHelper.tableName, null, version);
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

        leanmass = BMR*1.4;

        String leanmass2 = String.format("%.0f", leanmass);
        String BMR2 = String.format("%.0f", BMR);

        basicText = findViewById(R.id.basic);
        basicText.setText("기초                                 "+
                "                                         "+BMR2); //기초대사량
        recomText = findViewById(R.id.recommend);
        recomText.setText("권장                                 "+
                "                                         "+leanmass2); //권장섭취량

        basicBar = findViewById(R.id.basicBar);
        basicBar.setProgress(Integer.parseInt(BMR2));
        recomBar = findViewById(R.id.recomBar);
        recomBar.setProgress(Integer.parseInt(leanmass2));

        // 링크 입력부분
        // imageButton
        // 운동
        lf1 = findViewById(R.id.lf1);
        lf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=tPVzsTr7ULw"));
                startActivity(intent);
            }
        });
        lf2 = findViewById(R.id.lf2);
        lf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=V-Pg-MrVBeE"));
                startActivity(intent);
            }
        });
        lf3 = findViewById(R.id.lf3);
        lf3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=wbAZY5HPsbc"));
                startActivity(intent);
            }
        });
        lf4 = findViewById(R.id.lf4);
        lf4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=H4AZO-KiAPo"));
                startActivity(intent);
            }
        });
        lf5 = findViewById(R.id.lf5);
        lf5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=gTofe6nrSwA"));
                startActivity(intent);
            }
        });
        // 식단
        ls1 = findViewById(R.id.ls1);
        ls1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=WHqi90lNeX8&t=147s"));
                startActivity(intent);
            }
        });
        ls2 = findViewById(R.id.ls2);
        ls2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=w4rkqonvCSA"));
                startActivity(intent);
            }
        });
        ls3 = findViewById(R.id.ls3);
        ls3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=MWH8g6hCiWM"));
                startActivity(intent);
            }
        });
        ls4 = findViewById(R.id.ls4);
        ls4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=ApobDUjr7xc"));
                startActivity(intent);
            }
        });
        ls5 = findViewById(R.id.ls5);
        ls5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=aJWMx6NbmO0&t=282s"));
                startActivity(intent);
            }
        });
        // 보충제
        lb1 = findViewById(R.id.lb1);
        lb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=k8KUr8LzaaA"));
                startActivity(intent);
            }
        });
        lb2 = findViewById(R.id.lb2);
        lb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=Dj3kYRr5zDo&t=779s"));
                startActivity(intent);
            }
        });
        lb3 = findViewById(R.id.lb3);
        lb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=RpZ6RGd0JHU"));
                startActivity(intent);
            }
        });
        lb4 = findViewById(R.id.lb4);
        lb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=TulXF8GiReA"));
                startActivity(intent);
            }
        });
        lb5 = findViewById(R.id.lb5);
        lb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=AGcu3d1-suw"));
                startActivity(intent);
            }
        });
    }
}