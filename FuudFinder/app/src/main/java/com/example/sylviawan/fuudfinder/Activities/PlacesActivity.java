package com.example.sylviawan.fuudfinder.Activities;

import android.app.Dialog;
import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sylviawan.fuudfinder.R;

public class PlacesActivity extends AppCompatActivity {

    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        myDialog = new Dialog(this);

        Button backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity = new Intent(getApplicationContext(), Home.class);
                startActivity(homeActivity);
                finish();
            }
        });
    }

    public void ShowPopup(View view) {
        TextView txtclose;
        Button btnLike;
        myDialog.setContentView(R.layout.popup1);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        btnLike = (Button) myDialog.findViewById(R.id.btnLike);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();

            }
        });
        
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Added to Favorites List", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PlacesActivity.this, ListFragment.class);
                startActivity(intent);
            }

        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


}
