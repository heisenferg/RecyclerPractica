package com.example.recyclerpractica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.recyclerpractica.Canciones.Canciones;

public class MainActivity extends AppCompatActivity {

    Button open, pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        open = findViewById(R.id.buttonOpen);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CancionesActivity.class);
                startActivity(i);
            }
        });

    }


    public void reCarga(){
        Intent i = new Intent(getApplicationContext(), CancionesActivity.class);
        startActivity(i);
    }


}