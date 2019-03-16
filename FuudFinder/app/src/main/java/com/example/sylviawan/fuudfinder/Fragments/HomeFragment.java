package com.example.sylviawan.fuudfinder.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sylviawan.fuudfinder.R;

import javax.annotation.Nullable;

public class HomeFragment extends Fragment {

    public void onViewCreated(View view, @Nullable Bundle savedInstance){
        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Home");
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
