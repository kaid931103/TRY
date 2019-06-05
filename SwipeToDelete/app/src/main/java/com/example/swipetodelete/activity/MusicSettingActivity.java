package com.example.swipetodelete.activity;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.swipetodelete.R;

import java.io.IOException;

public class MusicSettingActivity extends AppCompatActivity {

    private Button play,pause;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);

        initView();

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
    }
    private void initView() {
        play = findViewById(R.id.btn_play);
        pause =findViewById(R.id.btn_pause);


        //开始播放
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mediaPlayer.prepare();
                } catch (IllegalStateException e) {

                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();

            }
        });

        //暂停播放
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });
    }
}
