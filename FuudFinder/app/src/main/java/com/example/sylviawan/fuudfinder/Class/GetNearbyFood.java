package com.example.sylviawan.fuudfinder.Class;

import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;

import java.io.IOException;

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

        super.onPostExecute(s);
    }
}
