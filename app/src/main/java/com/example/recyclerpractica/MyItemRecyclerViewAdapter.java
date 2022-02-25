package com.example.recyclerpractica;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerpractica.Canciones.Canciones;
import com.example.recyclerpractica.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.recyclerpractica.databinding.FragmentItemBinding;

import java.io.IOException;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>{
    public static MediaPlayer mediaPlayer = new MediaPlayer();
    public static MediaController mc;
    public static Handler h;
    private final List<Canciones.Cancion> mValues;
    public static String URI="";

    public MyItemRecyclerViewAdapter(List<Canciones.Cancion> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nombre.setText(mValues.get(position).getNombre());
        holder.descripcion.setText(mValues.get(position).getDescription());
        holder.caratula.setImageBitmap(mValues.get(position).getPhoto());

        URI = mValues.get(position).getURI();

        int clase = mValues.get(position).getTipo();

        if (clase ==0){
            holder.tipos.setImageResource(R.drawable.ic_nota_foreground);
        }
        else if (clase ==1){
         //   Video
            holder.tipos.setImageResource(R.drawable.ic_video_foreground);


        }
        else if (clase==2){
            //Streaming
            holder.tipos.setImageResource(R.drawable.ic_stream_foreground);

        }

        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URI = mValues.get(position).getURI();
                Log.d("URI", " es " + URI);



                if (clase ==0) {

                    //Audio

                    mc = new MediaController(v.getContext());
                    if (URI.equals("entersandman")) {
                        try {
                            Stop(v);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(v.getContext(), "Reproduciendo Enter Sandman", Toast.LENGTH_SHORT).show();
                        URI = "";
                        mediaPlayer = MediaPlayer.create(v.getContext(), R.raw.entersandman);

                    } else if (URI.equals("eltiempopasara")) {
                        try {
                            Stop(v);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        URI = "";
                        mediaPlayer = MediaPlayer.create(v.getContext(), R.raw.eltiempopasara);

                    }

                    mc.setMediaPlayer((MediaPlayerControl) v.getContext());
                    mc.setAnchorView(v.getRootView());
                    h = new Handler();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            h.post(new Runnable() {
                                @Override
                                public void run() {
                                    mc.show();
                                    mediaPlayer.start();
                                }
                            });
                        }
                    });

                }

                else if (clase == 1) {
                        //   Video
                    Intent i = new Intent(v.getContext(), VideoActivity.class);
                    // No reproduce así.
                    String URIVideo = "\"android.resource://\" + getPackageName() + \"/\" + R.raw.magia";

                    i.putExtra("URI", "magia");
                    v.getContext().startActivity(i);

                } else if (clase == 2) {
                        //Streaming
                    Intent i = new Intent(v.getContext(), VideoActivity.class);
                    i.putExtra("URI", mValues.get(position).getURI());
                    v.getContext().startActivity(i);

                }

            }
        });


    }

    public void Show(View v){
        mc.show();
    }
    public void Stop(View v) throws IOException {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.prepare();
        }
    }

    public void Hide(View v){
        mc.hide();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public TextView descripcion;
        public ImageView caratula;
        public ImageView tipos;
        public ImageView play;
        public LinearLayout cabecera;
        public String URI;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            nombre = binding.textViewNombre;
            descripcion = binding.textViewDescripciN;
            caratula = binding.caratula;
            play = binding.imageViewPlay;
            tipos = binding.imageViewTipo;
            cabecera = binding.linearLL;
        }

        @Override
        public String toString() {
            return super.toString() ;
        }
    }




}