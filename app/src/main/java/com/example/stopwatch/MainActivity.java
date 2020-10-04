package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private boolean whileRun=false;
    private boolean playTime = false;
    private int seconds = 0;

    private TextView textViewShowTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewShowTime = findViewById(R.id.textViewShowTime);
        if(savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds");
            playTime=savedInstanceState.getBoolean("playTime");
            whileRun=savedInstanceState.getBoolean("whileRun");

        }
        runTimer();
    }


    @Override
    protected void onPause() {
        super.onPause();
        whileRun=playTime;
        playTime=false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        whileRun=playTime;

    }

    public void voidRestart(View view) {
        playTime = false;
        seconds = 0;

    }

    public void voidStop(View view) {
        playTime = false;
    }

    public void voidStart(View view) {
        playTime = true;
    }


    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int sec = seconds % 60;

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, sec);
                textViewShowTime.setText(time);

                if (playTime) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });

    }
}