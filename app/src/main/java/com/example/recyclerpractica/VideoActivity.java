package com.example.recyclerpractica;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView videoView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoViewVideo);
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        String URI = getIntent().getExtras().getString("URI");
        Log.d("URI video", URI);
        if (URI.equals("magia")){
            videoView.setVideoURI(Uri.parse("android.resource://" +
                    getPackageName() + "/" + R.raw.magia));
        } else {
            videoView.setVideoURI(Uri.parse(URI));
        }
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaController.show(10000);
                    videoView.start();
                }
            });

    }

    @Override
    public boolean onTouchEvent (MotionEvent event){
        mediaController.show();
        return true;
    }
}