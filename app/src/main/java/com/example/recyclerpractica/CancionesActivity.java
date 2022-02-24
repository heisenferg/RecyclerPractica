package com.example.recyclerpractica;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.recyclerpractica.Canciones.Canciones;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.recyclerpractica.databinding.ActivityCancionesBinding;

public class CancionesActivity extends AppCompatActivity implements MediaController.MediaPlayerControl{
    public static MediaPlayer mediaPlayer;
    public static MediaController mc;
    public static Handler h;
    private AppBarConfiguration appBarConfiguration;
    private ActivityCancionesBinding binding;
    public static String URI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityCancionesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_canciones2);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        Canciones.loadBikesFromJSON(this);





       /* if (URI.equals("entersandman")) {

            mediaPlayer = MediaPlayer.create(this, R.raw.entersandman);
        } else if (URI.equals("eltiempopasara")){
            mediaPlayer = MediaPlayer.create(this, R.raw.eltiempopasara);

        }*/
        mediaPlayer = MediaPlayer.create(this, R.raw.eltiempopasara);

        mc = new MediaController(this);
        mc.setMediaPlayer((MediaController.MediaPlayerControl) this);
        mc.setAnchorView(findViewById(com.google.android.material.R.id.parentPanel));
        h = new Handler();
     /*   mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        mc.show(0);
                     //   mediaPlayer.start();

                    }
                });
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
            }
        });*/

    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_canciones2);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void reprodicirMp3(){

    }


    public void Show(View v){
        mc.show();
    }

    public void Hide(View v){
        mc.hide();
    }




    @Override
    public void start() {
        if (!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    @Override
    public void pause() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return mediaPlayer.getAudioSessionId();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
            if(!mc.isShowing())
                mc.show(0);
            else
                mc.hide();
        return false;
    }

}