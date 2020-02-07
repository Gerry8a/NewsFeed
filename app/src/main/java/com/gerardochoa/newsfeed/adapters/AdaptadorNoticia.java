package com.gerardochoa.newsfeed.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gerardochoa.newsfeed.R;
import com.gerardochoa.newsfeed.models.Noticia;

import java.util.ArrayList;

public class AdaptadorNoticia extends RecyclerView.Adapter<AdaptadorNoticia.NoticiasViewHolder> {

    private ArrayList<Noticia> mEstacionLista;
    private Context context;

    public static class NoticiasViewHolder extends RecyclerView.ViewHolder{

       // public ImageView mImageView;
        public TextView tvTitulo, tvFecha, tvFuente;

        /**
         * Método que inicializa los atributos de la cardView
         * @param itemView
         */
        public NoticiasViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFecha = itemView.findViewById(R.id.tvFechaPublicacion);
            tvFuente = itemView.findViewById(R.id.tvNoticiaFuente);
            tvTitulo = itemView.findViewById(R.id.tvNoticiaTitulo);


        }
    }

    public AdaptadorNoticia(ArrayList<Noticia> estacionLista) {
        mEstacionLista = estacionLista;
    }

    @NonNull
    @Override
    public NoticiasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptador_noticias, parent, false);
        NoticiasViewHolder evh = new NoticiasViewHolder(v);
        return  evh;
    }


    @Override
    public void onBindViewHolder(@NonNull NoticiasViewHolder holder, int position) {
        Noticia currenItem = mEstacionLista.get(position);
        holder.tvFuente.setText(currenItem.getFuente());
        holder.tvFecha.setText(currenItem.getFechaPublicacion());
        holder.tvTitulo.setText(currenItem.getTitulo());
    }

    @Override
    public int getItemCount() {
        return mEstacionLista.size();
    }
    /*
    private ArrayList<Noticia> listaNoticias;

    public static class NoticiasViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitulo, tvFuente, tvFecha;
        public NoticiasViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFecha = itemView.findViewById(R.id.tvFechaPublicacion);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvFuente = itemView.findViewById(R.id.tvNoticiaFuente);

        }
    }

    public AdaptadorNoticia(ArrayList<Noticia> noticias){
        listaNoticias = noticias;
    }

    @NonNull
    @Override
    public NoticiasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptador_noticias,
                parent, false);
        NoticiasViewHolder evh = new NoticiasViewHolder(view);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticiasViewHolder holder, int position) {
        Noticia currenNoticia = listaNoticias.get(position);
        holder.tvTitulo.setText(currenNoticia.getTitulo());
        holder.tvFecha.setText(currenNoticia.getFechaPublicacion());
        holder.tvFuente.setText(currenNoticia.getFuente());
    }

    @Override
    public int getItemCount() {
        return listaNoticias.size();
    }


     */





}
