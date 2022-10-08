package com.example.project2;

import static java.lang.Math.abs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;

import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapView;

public class RunActivity extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {

    private Chronometer chrono;
    private boolean running;
    private long pauseOffset;

    Button startBtn, stopBtn, resetBtn, run2;

    String API_Key = "l7xx307e334d60fa48ea83d967f7e14d88bb";

    // T Map View
    TMapView tMapView = null;

    // T Map GPS
    TMapGpsManager tMapGPS = null;

    // 멀티터치 이벤트
    private double touch_interval_X = 0; // X 터치 간격
    private double touch_interval_Y = 0; // Y 터치 간격
    private int zoom_in_count = 0; // 줌 인 카운트
    private int zoom_out_count = 0; // 줌 아웃 카운트
    private int touch_zoom = 0; // 줌 크기


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);

        //TMap
        Button buttonZoomIn = (Button)findViewById(R.id.buttonZoomIn);
        Button buttonZoomOut = (Button)findViewById(R.id.buttonZoomOut);

        // T Map View
        tMapView = new TMapView(this);

        // API Key
        tMapView.setSKTMapApiKey(API_Key);

        // Initial Setting
        tMapView.setZoomLevel(16);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);

        // T Map View Using Linear Layout
        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.linearLayoutTmap);
        linearLayoutTmap.addView(tMapView);

        // Request For GPS permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        // GPS using T Map
        tMapGPS = new TMapGpsManager(this);

        // Initial Setting
        tMapGPS.setMinTime(1000);    // 일정 시간마다 리셋
        tMapGPS.setMinDistance(10);  // 일정 거리마다 리셋
        //tMapGPS.setProvider(tMapGPS.NETWORK_PROVIDER); //네트워크
        tMapGPS.setProvider(tMapGPS.GPS_PROVIDER);       //GPS

        tMapGPS.OpenGps();


        //시간
        chrono = findViewById(R.id.chrono);
        chrono.setFormat("%s");

        startBtn = findViewById(R.id.startBtn);
        stopBtn = findViewById(R.id.stopBtn);
        resetBtn = findViewById(R.id.resetBtn);

        //시작버튼
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!running){
                    chrono.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    chrono.start();
                    running = true;
                }
            }
        });

        //정지버튼
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.stop();
                pauseOffset = SystemClock.elapsedRealtime() - chrono.getBase();
                running = false;
            }
        });

        //초기화버튼
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                chrono.stop();
                running = false;
            }
        });
    }

    // 지속적으로 위치를 받아와 설정해줌
    @Override
    public void onLocationChange(Location location) {
        tMapView.setLocationPoint(location.getLongitude(), location.getLatitude());
        tMapView.setCenterPoint(location.getLongitude(), location.getLatitude());
    }

    // 멀티터치(zoom in/out) 함수
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: // 싱글 터치
                break;
            case MotionEvent.ACTION_MOVE: // 터치 후 이동 시
                if (event.getPointerCount() == 2) { // 터치 손가락 2개일 때
                    double now_interval_X = (double) abs(event.getX(0) - event.getX(1)); // 두 손가락 X좌표 차이 절대값
                    double now_interval_Y = (double) abs(event.getY(0) - event.getY(1)); // 두 손가락 Y좌표 차이 절대값
                    if (touch_interval_X < now_interval_X && touch_interval_Y < now_interval_Y) { // 이전 값과 비교
                        // 확대 기능
                        tMapView.MapZoomIn();
                    }
                    if (touch_interval_X > now_interval_X && touch_interval_Y >
                            now_interval_Y) {
                        // 축소 기능
                        tMapView.MapZoomOut();
                    }
                    touch_interval_X = (double) abs(event.getX(0) - event.getX(1));
                    touch_interval_Y = (double) abs(event.getY(0) - event.getY(1));
                }
                break;
        }
            return super.onTouchEvent(event);
    }

}