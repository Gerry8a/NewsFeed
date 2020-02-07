package com.gerardochoa.newsfeed.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gerardochoa.newsfeed.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InternacionalFragment extends Fragment {


    public InternacionalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_internacional, container, false);
    }

}
