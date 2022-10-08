package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public ImageButton moveCal, run;

    int version = 1;
    DatabaseOpenHelper helper;
    SQLiteDatabase database;

    String sql;
    Cursor cursor;

    private BottomNavigationView bottomNavi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent getId = getIntent(); //전달할 데이터를 받을 Intent
        //text 키값으로 데이터를 받는다. String을 받아야 하므로 getStringExtra()를 사용함
        String id = getId.getStringExtra("text");

        bottomNavi = findViewById(R.id.bottonNavi);
        bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_home:
                        break;
                    case R.id.action_fitness:
                        Intent intent = new Intent(MainActivity.this, FitnessActivity.class);
                        intent.putExtra("text", id);
                        startActivity(intent);
                        break;
                    case R.id.action_info:
                        Intent intent2 = new Intent(MainActivity.this, InfoActivity.class);
                        intent2.putExtra("text", id);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });


        //DataBase연결부분
        helper = new DatabaseOpenHelper(MainActivity.this, DatabaseOpenHelper.tableName, null, version);
        database = helper.getWritableDatabase();

        sql = "SELECT * FROM "+ helper.tableName + " WHERE id = '" + id + "'";
        cursor = database.rawQuery(sql, null);
        cursor.moveToNext();   // 첫번째에서 다음 레코드가 없을때까지 읽음
        String name = cursor.getString(2);

        TextView welcome = findViewById(R.id.welcomeMessage);
        welcome.setText(name + "님 환영합니다!");

        // 러닝
        run = findViewById(R.id.runBtn);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RunActivity.class);
                startActivity(intent);
            }
        });


        CheckTypesTask task = new CheckTypesTask();
        // 운동일지
        moveCal = (ImageButton) findViewById(R.id.moveCal);
        moveCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.execute();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //String inputId = idEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                intent.putExtra("text", id);
                startActivity(intent);
                //finish();
            }
        });
    }
    private class CheckTypesTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog asyncDialog = new ProgressDialog(
                MainActivity.this);

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            asyncDialog.setMessage("운동일지를 불러오는중...");

            // show dialog
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                for (int i = 0; i < 5; i++) {
                    asyncDialog.setProgress(i * 20);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            asyncDialog.dismiss();
            super.onPostExecute(result);
        }
    }

}