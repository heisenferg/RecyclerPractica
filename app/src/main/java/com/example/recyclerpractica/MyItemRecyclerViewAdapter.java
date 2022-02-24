package com.example.recyclerpractica;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerpractica.Canciones.Canciones;
import com.example.recyclerpractica.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.recyclerpractica.databinding.FragmentItemBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> implements MediaPlayerControl {

    MediaPlayer mediaPlayer, mediaPlayer2;
    MediaController mc, mc2;
    Handler h;
    Canciones canciones = new Canciones();
    private final List<Canciones.Cancion> mValues;
    Context context;
    CancionesActivity reproductor = new CancionesActivity();
    public static String URI="";

    public MyItemRecyclerViewAdapter(List<Canciones.Cancion> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
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
                if (clase ==0){
                    //Audio
                    // Para poder controlar que arranque, he utilizado esta solución.

                   // reproductor.start();
                    if (URI.equals("entersandman")){
                        URI="";

                        Log.d("URI Aqui " , URI);
                        mediaPlayer = MediaPlayer.create(holder.caratula.getContext(), R.raw.entersandman);

                    }
                    else if (URI.equals("eltiempopasara")) {
                        URI="";
                        mediaPlayer2 = MediaPlayer.create(holder.caratula.getContext(), R.raw.eltiempopasara);
                    /*    mc2 = new MediaController(holder.caratula.getContext());
                        mc2.setMediaPlayer((MediaController.MediaPlayerControl) holder.caratula.getContext());
                        mc2.setAnchorView(holder.cabecera);
                        h = new Handler();
                       // mc.show();
                        mediaPlayer2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                h.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mc2.show(0);
                                        mediaPlayer2.start();

                                    }
                                });
                            }
                        });
                        mediaPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mediaPlayer2.release();
                            }
                        });*/

                    }
                    mc = new MediaController(holder.caratula.getContext());
                    mc.setMediaPlayer((MediaController.MediaPlayerControl) holder.caratula.getContext());
                    mc.setAnchorView(holder.cabecera.getRootView());
                    h = new Handler();
                    // mc.show();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            h.post(new Runnable() {
                                @Override
                                public void run() {
                                    mediaPlayer.start();
                                    mc.show(10000);
                                }
                            });
                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.release();
                        }
                    });





                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.release();
                        }
                    });
                }
                else if (clase ==1){
                    //   Video



                }
                else if (clase==2){
                    //Streaming


                }
            }
        });


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
        public RelativeLayout cabecera;
        public String URI;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            nombre = binding.textViewNombre;
            descripcion = binding.textViewDescripciN;
            caratula = binding.caratula;
            play = binding.imageViewPlay;
            tipos = binding.imageViewTipo;
            cabecera = binding.relative;
        }

        @Override
        public String toString() {
            return super.toString() ;
        }
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

  /*  @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
            if(!mc.isShowing())
                mc.show(0);
            else
                mc.hide();
        return false;
    }*/


    @Override
    public int getAudioSessionId() {
        return mediaPlayer.getAudioSessionId();
    }



}