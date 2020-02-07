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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gerardochoa.newsfeed.R;
import com.gerardochoa.newsfeed.adapters.AdaptadorNoticia;
import com.gerardochoa.newsfeed.models.Noticia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NacionalFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorNoticia adaptadorNoticia;
    private ArrayList<Noticia> noticias;
    private RequestQueue requestQueue;


    public NacionalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_internacional, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewInternacional);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        noticias = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(getContext());
        parseJSON();
        return view;
    }

    private void parseJSON() {
        String url = "https://newsapi.org/v2/top-headlines?country=mx&apiKey=2a5d404a474c4f50a62ac30dcf07a0ed";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("articles");
                    llenarLista(jsonArray);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void llenarLista(JSONArray jsonArray) throws JSONException {
        for(int i = 0; i < jsonArray.length(); i ++){
            JSONObject noticiaJSON = jsonArray.getJSONObject(i);
            agregarNoticia(noticiaJSON);
        }
        AdaptadorNoticia adaptadorNoticia = new AdaptadorNoticia(noticias, getContext());
        recyclerView.setAdapter(adaptadorNoticia);
    }



    private void agregarNoticia(JSONObject noticiaJSON) throws JSONException {
        String titulo = noticiaJSON.getString("title");
        String fecha = noticiaJSON.getString("publishedAt");
        String autor = noticiaJSON.getString("author");
        String imagen = noticiaJSON.getString("urlToImage");

        Noticia noticia = new Noticia(titulo, autor, fecha, imagen);
        noticias.add(noticia);
    }



}
