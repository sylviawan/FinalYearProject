package com.example.sylviawan.fuudfinder.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.sylviawan.fuudfinder.Activities.LoginActivity;
import com.example.sylviawan.fuudfinder.Activities.PlacesActivity;
import com.example.sylviawan.fuudfinder.R;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<String> arrL;
    private ArrayAdapter<String> arrayAdapter;
    private int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getActivity().setTitle("Home");

        arrL = new ArrayList<>();
        arrL.add("Casual?");
        arrL.add("Cozy?");
        arrL.add("Asian?");
        arrL.add("Budget?");
        arrL.add("Fancy?");
        arrL.add("Burgers?");
        arrL.add("Coffee?");
        arrL.add("Cafe?");
        arrL.add("Restaurant?");
        arrL.add("Do you want a reservation?");

        arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.items, R.id.welcomeText, arrL);

        final SwipeFlingAdapterView flingContainer = view.findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (AdapterView)
                Log.d("LIST", "removed object!");
                arrL.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(getActivity(), "No", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(getActivity(), "Yes", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
//                arrL.add("More to come ");
                arrayAdapter.notifyDataSetChanged();
//                i++;
                Intent placesActivity = new Intent(getActivity(), PlacesActivity.class);
                startActivity(placesActivity);
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

        return view;
    }

}
