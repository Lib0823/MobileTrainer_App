package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    int version = 1;
    DatabaseOpenHelper helperUser, helperRecord;
    SQLiteDatabase databaseUser, databaseRecord;

    EditText idEditText;
    EditText pwEditText;
    Button btnLogin;
    Button btnJoin;

    String sql;
    Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView intro = (ImageView)findViewById(R.id.introImage);
        Glide.with(this).load(R.raw.intro_mobile_).into(intro);

        idEditText = (EditText) findViewById(R.id.idEditText);
        pwEditText = (EditText) findViewById(R.id.pwEditText);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnJoin = (Button) findViewById(R.id.btnJoin);

        helperUser = new DatabaseOpenHelper(LoginActivity.this, DatabaseOpenHelper.tableName, null, version);
        databaseUser = helperUser.getWritableDatabase();
        helperRecord = new DatabaseOpenHelper(LoginActivity.this, DatabaseOpenHelper.tableNameRecord, null, version);
        databaseRecord = helperRecord.getWritableDatabase();

        // Request For GPS permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String id = idEditText.getText().toString();
                String pw = pwEditText.getText().toString();

                if(id.length() == 0 || pw.length() == 0) {
                    //아이디와 비밀번호를 입력해주세요.
                    Toast toast = Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                sql = "SELECT id FROM "+ helperUser.tableName + " WHERE id = '" + id + "'";
                cursor = databaseUser.rawQuery(sql, null);

                if(cursor.getCount() != 1){
                    //아이디가 틀렸습니다.
                    Toast toast = Toast.makeText(LoginActivity.this, "아이디가 틀렸습니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                sql = "SELECT pw FROM "+ helperUser.tableName + " WHERE id = '" + id + "'";
                cursor = databaseUser.rawQuery(sql, null);

                cursor.moveToNext();
                if(!pw.equals(cursor.getString(0))){
                    //비밀번호가 틀렸습니다.
                    Toast toast = Toast.makeText(LoginActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT);
                    toast.show();
                }else{ // 로그인 성공
                    // 현재 날짜 가져오기
                    long now = System.currentTimeMillis();
                    Date date = new Date(now);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                    String dateNow = sdf.format(date);
                    // 저장된 날짜 가져오기
                    sql = "SELECT date FROM "+ helperUser.tableName + " WHERE id = '" + id + "'";
                    cursor = databaseUser.rawQuery(sql, null);
                    cursor.moveToNext();
                    String dateSave = cursor.getString(0);

                    // 저장된 날짜가 현재 날짜와 다르다면
                    if(!dateSave.equals(dateNow)){

                        // Run값 초기화
                        databaseUser.execSQL("UPDATE Users SET " +
                                "run=0 WHERE id ='" + id + "'");

                        // 다시 현재 날짜 세팅
                        databaseUser.execSQL("UPDATE Users SET " +
                                "date='"+dateNow+"' WHERE id ='" + id + "'");

                        int time = 0;
                        double distance = 0.0;
                        int step = 0;
                        double kcal = 0.0;

                        // Record에 삽입할 현재 날짜
                        long now2 = System.currentTimeMillis();
                        Date date2 = new Date(now2);
                        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd");
                        String dateNow2 = sdf2.format(date2);

                        // 새로운 Record값 insert
                        helperRecord.insertRecord(databaseRecord,id, dateNow2, time, distance, step, kcal);

                    }


                    //login값 초기화 / 세팅
                    databaseUser.execSQL("UPDATE Users SET " +
                            "login='0' WHERE NOT id ='" + id + "'");
                    databaseUser.execSQL("UPDATE Users SET " +
                            "login='1' WHERE id ='" + id + "'");
                    //인텐트 생성 및 호출
                    String inputId = idEditText.getText().toString();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("text", inputId);
                    startActivity(intent);
                    finish();
                }
                cursor.close();
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //회원가입 버튼 클릭
                Toast toast = Toast.makeText(LoginActivity.this, "회원가입 화면으로 이동", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getApplicationContext(),JoinActivity.class);
                startActivity(intent);
                //finish();
            }
        });


    }
}