package com.example.sylviawan.fuudfinder.Class;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.sylviawan.fuudfinder.R;

public class FoodViewHolder extends RecyclerView.ViewHolder {

    public View view;
    public TextView fname, faddress, ftype, frating;


    public FoodViewHolder(View itemView) {

        super(itemView);
        view = itemView;
        fname = itemView.findViewById(R.id.foodname);
        frating = itemView.findViewById(R.id.foodrating);
        faddress = itemView.findViewById(R.id.foodaddr);
    }

}
