package com.example.recyclerpractica;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.recyclerpractica.Canciones.Canciones;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class Ajustes extends PreferenceFragmentCompat {

    public Ajustes() {
        // Required empty public constructor

    }

    @Override
    public void onDestroy() {
        MyItemRecyclerViewAdapter adapter = new MyItemRecyclerViewAdapter(Canciones.ITEMS);
        adapter.notifyDataSetChanged();
        super.onDestroy();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferencias, rootKey);
    }

}