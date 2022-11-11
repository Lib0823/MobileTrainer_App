package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {

    int version = 1;
    DatabaseOpenHelper helperU, helperR;
    SQLiteDatabase databaseU, databaseR;

    String sql;
    Cursor cursor;

    private String id, dateBtn;
    private String dateArr[] = {"Null","Null","Null","Null","Null","Null","Null","Null","Null","Null","Null"};
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10;

    private ProgressBar timePro, distancePro, stepPro, kcalPro;
    private TextView timeText, distanceText, stepText, kcalText, dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);

        //DataBase연결부분
        helperU = new DatabaseOpenHelper(RecordActivity.this, DatabaseOpenHelper.tableName, null, version);
        databaseU = helperU.getWritableDatabase();

        sql = "SELECT * FROM "+ helperU.tableName + " WHERE login = '1'";
        cursor = databaseU.rawQuery(sql, null);
        cursor.moveToNext();   // 첫번째에서 다음 레코드가 없을때까지 읽음
        id = cursor.getString(0);

        // 날짜 가져오기
        helperR = new DatabaseOpenHelper(RecordActivity.this, DatabaseOpenHelper.tableNameRecord, null, version);
        databaseR = helperR.getWritableDatabase();

        sql = "SELECT * FROM "+ helperR.tableNameRecord + " WHERE id = '"+id+"'";
        cursor = databaseR.rawQuery(sql, null);
        int i=0;
        while(cursor.moveToNext()){
            dateArr[i] = cursor.getString(1);
            i++;
        }

        btn1.setText(dateArr[0]);
        btn2.setText(dateArr[1]);
        btn3.setText(dateArr[2]);
        btn4.setText(dateArr[3]);
        btn5.setText(dateArr[4]);
        btn6.setText(dateArr[5]);
        btn7.setText(dateArr[7]);
        btn8.setText(dateArr[8]);
        btn9.setText(dateArr[9]);
        btn10.setText(dateArr[10]);

        timePro = findViewById(R.id.timePro);
        distancePro = findViewById(R.id.distancePro);
        stepPro = findViewById(R.id.stepPro);
        kcalPro = findViewById(R.id.kcalPro);

        timeText = findViewById(R.id.timeText);
        distanceText = findViewById(R.id.distanceText);
        stepText = findViewById(R.id.stepText);
        kcalText = findViewById(R.id.kcalText);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateBtn = btn1.getText().toString();
                dataSetting(dateBtn);
            }
        });







    }
    public void dataSetting(String dateBtn){
        String[] dateArr = dateBtn.split("/");
        dateText = findViewById(R.id.dateText);
        dateText.setText(dateArr[0]+"월 "+dateArr[1]+"일");

        int time, step;
        double distance, kcal;
        // DB에서 현재 id의 해당 날짜 데이터 가져오기
        sql = "SELECT * FROM "+ helperR.tableNameRecord + " WHERE id = '"+id+"' AND date = '"+dateBtn+"'";
        cursor = databaseR.rawQuery(sql, null);
        cursor.moveToNext();
        time = cursor.getInt(2); // 시간
        distance = cursor.getDouble(3); // 거리
        step = cursor.getInt(4); // 걸음 수
        kcal = cursor.getDouble(5); // 칼로리

        int distance2 = (int)distance;
        int kcal2 = (int)kcal;

        // DB에서 가져온 값 textview에 세팅
        timeText.setText(String.valueOf(time)+"분");
        distanceText.setText(String.valueOf(distance2)+"km");
        stepText.setText(String.valueOf(step)+"걸음");
        kcalText.setText(String.valueOf(kcal2)+"kcal");

        // DB에서 가져온 값 ProgressBar에 삽입
        timePro.setProgress(time);
        distancePro.setProgress(distance2);
        stepPro.setProgress(step);
        kcalPro.setProgress(kcal2);
    }

}