package com.example.recyclerpractica;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;

import com.example.recyclerpractica.Canciones.Canciones;


import androidx.appcompat.app.AppCompatActivity;



import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.recyclerpractica.databinding.ActivityCancionesBinding;

public class CancionesActivity extends AppCompatActivity implements MediaController.MediaPlayerControl {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCancionesBinding binding;



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



    }


    @Override
    public void start() {
        MyItemRecyclerViewAdapter.mediaPlayer.start();
    }

    @Override
    public void pause() {
        if (MyItemRecyclerViewAdapter.mediaPlayer.isPlaying()) {
            MyItemRecyclerViewAdapter.mediaPlayer.pause();
        }
    }

    @Override
    public int getDuration() {
        return MyItemRecyclerViewAdapter.mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return MyItemRecyclerViewAdapter.mediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        MyItemRecyclerViewAdapter.mediaPlayer.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return MyItemRecyclerViewAdapter.mediaPlayer.isPlaying();
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
        return MyItemRecyclerViewAdapter.mediaPlayer.getAudioSessionId();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
            if(MyItemRecyclerViewAdapter.mc.isShowing())
                MyItemRecyclerViewAdapter.mc.hide();
            else
                MyItemRecyclerViewAdapter.mc.show(0);

        return super.onTouchEvent(event);
    }

}