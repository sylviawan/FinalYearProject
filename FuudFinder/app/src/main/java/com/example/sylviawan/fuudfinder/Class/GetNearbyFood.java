package com.example.sylviawan.fuudfinder.Class;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearbyFood extends AsyncTask {

    private String googlePlaceData, gUrl;
    private GoogleMap mMap;

    @Override
    protected Object doInBackground(Object[] objects) {

        mMap = (GoogleMap) objects[0];
        gUrl = (String) objects[1];

        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            googlePlaceData = downloadUrl.ReadUrl(gUrl);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return googlePlaceData;

    }


    protected void onPostExecute(String s) {

        List<HashMap<String,String>> nearFoodList = null;
        DataParser dataParser = new DataParser();
        nearFoodList = dataParser.parse(s);
        ShowNearbyFood(nearFoodList);
    }

    private void ShowNearbyFood(List<HashMap<String,String>> nearFoodList)
    {
        for (int i=0; i<nearFoodList.size(); i++)
        {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String,String> googleNearbyPlace = nearFoodList.get(i);
            String nameOfPlace = googleNearbyPlace.get("place_name");
            String vicinity = googleNearbyPlace.get("vicinity");
            double lat = Double.parseDouble(googleNearbyPlace.get("lat"));
            double lng = Double.parseDouble(googleNearbyPlace.get("lng"));

            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(nameOfPlace + " : " + vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        }
    }
}
