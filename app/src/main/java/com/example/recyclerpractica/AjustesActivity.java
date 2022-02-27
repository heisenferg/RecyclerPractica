package com.example.recyclerpractica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AjustesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorAjustes, new Ajustes()).commit();
    }

}