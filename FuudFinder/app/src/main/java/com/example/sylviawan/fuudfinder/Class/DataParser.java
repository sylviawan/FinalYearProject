package com.example.sylviawan.fuudfinder.Class;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParser {

    // Storing places
    private HashMap<String, String> getPlace(JSONObject googlePlaceJSON) {
        HashMap<String, String> googlePlaceMap = new HashMap<>();

        String nameOfPlace = "NA";
        String vicinity = "NA";
        String latitude = "";
        String longitude = "";
        String reference = "";

        try {
            if (!googlePlaceJSON.isNull("name"))
            {
                nameOfPlace = googlePlaceJSON.getString("name");
            }
            if (!googlePlaceJSON.isNull("vicinity"))
            {
                nameOfPlace = googlePlaceJSON.getString("vicinity");
            }

            latitude = googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference = googlePlaceJSON.getString("reference");

            googlePlaceMap.put("place_name", nameOfPlace);
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("lat", latitude);
            googlePlaceMap.put("lng", longitude);
            googlePlaceMap.put("reference", reference);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return googlePlaceMap;
    }

    private List<HashMap<String,String>> getAllNearbyFood(JSONArray jsonArray){
        int counter = jsonArray.length();

        List<HashMap<String,String>> NearbyFoodList = new ArrayList<>();

        HashMap<String, String> NearbyPlaceMap = null;

        for (int i = 0; i<counter; i++)
        {
            try {
                NearbyPlaceMap = getPlace((JSONObject) jsonArray.get(i));
                NearbyFoodList.add(NearbyPlaceMap);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return NearbyFoodList;
    }

    // Parsing the data to the getJSONArray
    public List<HashMap<String, String>> parse(String JSONdata)
    {
        JSONArray jsonArray = null;
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(JSONdata);
            jsonArray = jsonObject.getJSONArray("results");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return getAllNearbyFood(jsonArray);
    }
}
