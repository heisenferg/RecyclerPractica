package com.example.recyclerpractica.Canciones;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Canciones {
    //List of all the bikes to be listed in the RecyclerView
    public static List<Cancion> ITEMS = new ArrayList<Cancion>();
    public static String selectedDate; //Store the selected date
    public static void loadBikesFromJSON(Context c) {
        String json = null;
        try {
            InputStream is =
                    c.getAssets().open("recursosList.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray couchList = jsonObject.getJSONArray("recursos_list");
            for (int i = 0; i < couchList.length(); i++) {
                JSONObject jsonCouch = couchList.getJSONObject(i);
                String nombre = jsonCouch.getString("nombre");
                String description = jsonCouch.getString("descripcion");
                int tipo=jsonCouch.getInt("tipo");
                String URI=jsonCouch.getString("URI");
                Bitmap photo=null;
                try {
                    photo= BitmapFactory.decodeStream(
                            c.getAssets().open("images/"+
                                    jsonCouch.getString("imagen")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ITEMS.add(new Canciones.Cancion(
                        photo,nombre,description,tipo,URI));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    public static class Cancion {
        private Bitmap photo;
        private String nombre;
        private String description;
        private int tipo;
        private String URI;
        //Setters and getters...
        public Cancion(Bitmap photo, String nombre, String description,
                    int tipo, String URI) {
            this.photo = photo;
            this.nombre = nombre;
            this.description = description;
            this.tipo = tipo;
            this.URI = URI;
        }

        public Bitmap getPhoto() {
            return photo;
        }

        public void setPhoto(Bitmap photo) {
            this.photo = photo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getTipo() {
            return tipo;
        }

        public void setTipo(int tipo) {
            this.tipo = tipo;
        }

        public String getURI() {
            return URI;
        }

        public void setURI(String URI) {
            this.URI = URI;
        }

        @Override
        public String toString() {
            return nombre+" "+description;
        }
    }
}
