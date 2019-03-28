package com.example.sylviawan.fuudfinder.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sylviawan.fuudfinder.R;

import javax.annotation.Nullable;

public class ListsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lists, container, false);
        getActivity().setTitle("Favourites");
        return view;
    }


}
