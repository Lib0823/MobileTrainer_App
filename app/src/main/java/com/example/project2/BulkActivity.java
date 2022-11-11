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

public class BulkActivity extends AppCompatActivity {

    int version = 1;
    DatabaseOpenHelper helper;
    SQLiteDatabase database;

    String sql;
    Cursor cursor;

    Double BMR, bulkup;
    TextView basicText, recomText;
    ProgressBar basicBar, recomBar;

    private ImageButton bf1, bf2, bf3, bf4, bf5;
    private ImageButton bs1, bs2, bs3, bs4, bs5;
    private ImageButton bb1, bb2, bb3, bb4, bb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk);

        //DataBase연결부분
        helper = new DatabaseOpenHelper(BulkActivity.this, DatabaseOpenHelper.tableName, null, version);
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

        bulkup = (BMR*1.4)*1.2;

        String bulkup2 = String.format("%.0f", bulkup);
        String BMR2 = String.format("%.0f", BMR);

        basicText = findViewById(R.id.basic);
        basicText.setText("기초                                 "+
                "                                         "+BMR2); //기초대사량
        recomText = findViewById(R.id.recommend);
        recomText.setText("권장                                 "+
                "                                         "+bulkup2); //권장섭취량

        basicBar = findViewById(R.id.basicBar);
        basicBar.setProgress(Integer.parseInt(BMR2));
        recomBar = findViewById(R.id.recomBar);
        recomBar.setProgress(Integer.parseInt(bulkup2));

        // 링크 입력부분
        // imageButton
        // 운동
        bf1 = findViewById(R.id.bf1);
        bf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=J4TMWaxPQKk"));
                startActivity(intent);
            }
        });
        bf2 = findViewById(R.id.bf2);
        bf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=wGVMv2pOZcg&t=94s"));
                startActivity(intent);
            }
        });
        bf3 = findViewById(R.id.bf3);
        bf3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=XCCeqDwmCFQ"));
                startActivity(intent);
            }
        });
        bf4 = findViewById(R.id.bf4);
        bf4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=szOEoAzw7YU"));
                startActivity(intent);
            }
        });
        bf5 = findViewById(R.id.bf5);
        bf5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=bD9zSgeVUkY"));
                startActivity(intent);
            }
        });
        // 식단
        bs1 = findViewById(R.id.bs1);
        bs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=Z--DYp5dPw4"));
                startActivity(intent);
            }
        });
        bs2 = findViewById(R.id.bs2);
        bs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=ZDwNHTDuLRs"));
                startActivity(intent);
            }
        });
        bs3 = findViewById(R.id.bs3);
        bs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=XCCeqDwmCFQ&t=49s"));
                startActivity(intent);
            }
        });
        bs4 = findViewById(R.id.bs4);
        bs4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=d32FMg9tzEo"));
                startActivity(intent);
            }
        });
        bs5 = findViewById(R.id.bs5);
        bs5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=xkBAiu8MmlM"));
                startActivity(intent);
            }
        });
        // 보충제
        bb1 = findViewById(R.id.bb1);
        bb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=EFM2I2icWqE"));
                startActivity(intent);
            }
        });
        bb2 = findViewById(R.id.bb2);
        bb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=qhD6wucVhso"));
                startActivity(intent);
            }
        });
        bb3 = findViewById(R.id.bb3);
        bb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=sgRLOXSOqNk"));
                startActivity(intent);
            }
        });
        bb4 = findViewById(R.id.bb4);
        bb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=Ki_1jOjbxpU"));
                startActivity(intent);
            }
        });
        bb5 = findViewById(R.id.bb5);
        bb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=Rbqm8YBbq4Q&t=286s"));
                startActivity(intent);
            }
        });

    }
}