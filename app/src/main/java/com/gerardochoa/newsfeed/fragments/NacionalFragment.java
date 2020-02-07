package com.gerardochoa.newsfeed.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.gerardochoa.newsfeed.R;
import com.gerardochoa.newsfeed.adapters.AdaptadorNoticia;
import com.gerardochoa.newsfeed.models.Noticia;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NacionalFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Noticia> listaNoticias;
    private TextView tvTitulo, tvFuente, tvFecha;
    private AdaptadorNoticia adaptadorNoticia;
    private RecyclerView.LayoutManager layoutManager;

    public NacionalFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nacional, container, false);
        listaNoticias = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerViewNacional);
        tvFecha = view.findViewById(R.id.tvFechaPublicacion);
        tvTitulo = view.findViewById(R.id.tvTitulo);
        tvFuente = view.findViewById(R.id.tvNoticiaFuente);
        listaNoticias.add(new Noticia("La poderos", "Yo merengues", "Hoy"));
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        final AdaptadorNoticia adaptadorNoticia = new AdaptadorNoticia(listaNoticias);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorNoticia);




        return view;
    }

    private void inicializarComponentes(View view) {

    }

    private void llenarLista() {
        listaNoticias.add(new Noticia("La poderos", "Yo merengues", "Hoy"));
    }

}
