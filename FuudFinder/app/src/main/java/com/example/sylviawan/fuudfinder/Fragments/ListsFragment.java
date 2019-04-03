package com.example.sylviawan.fuudfinder.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sylviawan.fuudfinder.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListsFragment extends Fragment {

    ListView listview;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mref = database.getReference();
    FirebaseListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lists, container, false);
        getActivity().setTitle("Favourites");


        ListView listView = view.findViewById(R.id.list_view);

//        FirebaseListOptions<FoodList> options = new FirebaseListOptions.Builder<FoodList>()
//                .setLayout(R.id.foods)
//                .setQuery(query,FoodList.class)
//                .build();

//        Firebase ref = Firebase("https://fuudfinder-a069d.firebaseio.com/Person");

        /*
         * Create a DatabaseReference to the data; works with standard DatabaseReference methods
         * like limitToLast() and etc.
         */
//        DatabaseReference peopleReference = FirebaseDatabase.getInstance().getReference()
//                .child("people");

//        // Now set the adapter with a given layout
//        listView.setAdapter(new FirebaseListAdapter<FoodList>(getActivity(), FoodList.class, peopleReference) {
//
//            // Populate view as needed
//            @Override
//            protected void populateView(View view, FoodList person, int position) {
//                ((TextView) view.findViewById(android.R.id.text1)).setText(person.getName());
//            }
//        });
//        User rootRef = FirebaseDatabase.getInstance().getReference();
//        User usersRef = rootRef.child("Time");
//        ValueEventListener eventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                    String email = ds.child("email").getValue(String.class);
//                    String time = ds.child("time").getValue(String.class);
//                    Log.d("TAG", email + " / " + time); // logcat check value
//                }
//        }

        return view;
    }


}
