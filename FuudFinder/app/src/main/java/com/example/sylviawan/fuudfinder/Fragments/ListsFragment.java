package com.example.sylviawan.fuudfinder.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sylviawan.fuudfinder.Class.Food;
import com.example.sylviawan.fuudfinder.Class.FoodViewHolder;
import com.example.sylviawan.fuudfinder.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter_LifecycleAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mref = database.getReference();
    FirebaseListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lists, container, false);
        getActivity().setTitle("Favourites");


        recyclerView = view.findViewById(R.id.list_item);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

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

    @Override
    public void onStart() {

        super.onStart();

        final DatabaseReference foodListRef = FirebaseDatabase.getInstance().getReference().child("Food");

        FirebaseRecyclerOptions<Food> options = new FirebaseRecyclerOptions.Builder<Food>()
                .setQuery(foodListRef.child("food1"), Food.class)
                .build();

        FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder holder, int position, @NonNull Food model) {
                holder.frating.setText(model.getRating());
                holder.faddress.setText(model.getAddress());
                holder.fname.setText(model.getName());

                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),"clicked", Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.fave_item_layout,null);

                FoodViewHolder holder = new FoodViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
//
//    private void build() {
//    }
//
//    private DatabaseReference setQuery(DatabaseReference foodListRef) {
//        return null;
//    }

}
