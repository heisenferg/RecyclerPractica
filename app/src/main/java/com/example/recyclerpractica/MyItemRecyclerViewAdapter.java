package com.example.recyclerpractica;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerpractica.Canciones.Canciones;
import com.example.recyclerpractica.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.recyclerpractica.databinding.FragmentItemBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    Canciones canciones = new Canciones();
    private final List<Canciones.Cancion> mValues;
    Context context;

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
        int clase = mValues.get(position).getTipo();
        if (clase ==0){
            holder.tipos.setImageBitmap(mValues.get(position).getPhoto());
        }
        else if (clase ==1){
         //   holder.tipos.setImageBitmap();

        }
        else if (clase==2){

        }


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

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            nombre = binding.textViewNombre;
            descripcion = binding.textViewDescripciN;
            caratula = binding.caratula;
            play = binding.imageViewPlay;
            tipos = binding.imageViewTipo;
        }

        @Override
        public String toString() {
            return super.toString() ;
        }
    }
}