package com.example.recyclerpractica;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;

import com.example.recyclerpractica.Canciones.Canciones;
import com.example.recyclerpractica.databinding.ActivityCancionesBinding;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.util.List;


public class CancionesActivity extends AppCompatActivity implements MediaController.MediaPlayerControl {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCancionesBinding binding;
    public static boolean audio, video, streaming;
    public MyItemRecyclerViewAdapter myItemRecyclerViewAdapter= new MyItemRecyclerViewAdapter(Canciones.ITEMS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityCancionesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_canciones2);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        guardarPreferencias();
        Canciones.loadBikesFromJSON(this);
    }



    public void guardarPreferencias (){
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);

        audio = preferencias.getBoolean("audio", true);
        video = preferencias.getBoolean("video", true);
        streaming = preferencias.getBoolean("streaming", true);


        Log.d("Preferencias: ", "Opcion 1 " + preferencias.getBoolean("audio", true));

    }


    @Override
    public void start() {
        MyItemRecyclerViewAdapter.mediaPlayer.start();
        myItemRecyclerViewAdapter.notifyDataSetChanged();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.settings){
            Toast.makeText(this, "Abriendo configuración...", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), AjustesActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


}