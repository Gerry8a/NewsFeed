package com.gerardochoa.newsfeed.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private RecyclerView recyclerView;
    private ArrayList<Noticia> noticias;
    private RequestQueue requestQueue;
    String url = "https://newsapi.org/v2/top-headlines?country=mx&apiKey=2a5d404a474c4f50a62ac30dcf07a0ed";

    public NacionalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_internacional, container, false);

        solicitarPermisos();


        recyclerView = view.findViewById(R.id.recyclerViewInternacional);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        noticias = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(getContext());
        parseJSON(url);


        return view;
    }

    private void solicitarPermisos() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                new AlertDialog.Builder(getContext())
                        .setTitle(R.string.location_message)
                        .setMessage(getString(R.string.location_message))
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_LOCATION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            } else {
                solicitarPermisos();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * Método para consimir el API
     */
    private void parseJSON(String url) {

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

    /**
     * Método para llenar la lista de objetos de tipo Noticia a partir de un JSONArray con
     la informacion de todas las noticias.
     * @param jsonArray
     * @throws JSONException
     */
    private void llenarLista(JSONArray jsonArray) throws JSONException {
        for(int i = 0; i < jsonArray.length(); i ++){
            JSONObject noticiaJSON = jsonArray.getJSONObject(i);
            agregarNoticia(noticiaJSON);
        }
        AdaptadorNoticia adaptadorNoticia = new AdaptadorNoticia(noticias, getContext());
        recyclerView.setAdapter(adaptadorNoticia);
    }


    /**
     *  Metodo agregarActividad que agrega un objeto de tipo Noticia a la lista de noticias
     * @param noticiaJSON
     * @throws JSONException
     */
    private void agregarNoticia(JSONObject noticiaJSON) throws JSONException {
        String titulo = noticiaJSON.getString("title");
        String fecha = noticiaJSON.getString("publishedAt");
        String autor = noticiaJSON.getString("author");
        String imagen = noticiaJSON.getString("urlToImage");

        Noticia noticia = new Noticia(titulo, autor, fecha, imagen);
        noticias.add(noticia);
    }



}
