package com.dsassignment.sleepscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NightSong extends AppCompatActivity {

    Button sleep;
    Button pause;
    MediaPlayer sleepSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        sleep = findViewById(R.id.sleep);
        pause = findViewById(R.id.pause);
        sleepSong = MediaPlayer.create(NightSong.this,R.raw.song);

        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sleepSong == null) {
                    sleepSong = MediaPlayer.create(NightSong.this, R.raw.song);
                    sleepSong.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                        @Override
                        public void onCompletion(MediaPlayer mp){
                            sleepSong.stop();
                        }
                    });
                }

                sleepSong.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sleepSong !=null){
                    sleepSong.release();
                    sleepSong = null;
                    //alarm message
                }
            }
        });
    }
}