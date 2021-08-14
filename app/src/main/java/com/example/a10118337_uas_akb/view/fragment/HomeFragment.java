package com.example.a10118337_uas_akb.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a10118337_uas_akb.R;
import com.example.a10118337_uas_akb.model.DataDestinasi;
import com.example.a10118337_uas_akb.view.MapsActivity;
import com.example.a10118337_uas_akb.view.adapter.RecycleAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{
    List<DataDestinasi> list;
    RecycleAdapter recycleAdapter;
    RecyclerView recyclerView;
    DatabaseReference database;
    public FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        FloatingActionButton fab = view.findViewById(R.id.button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = view.findViewById(R.id.recycleView);

        read();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void read() {
        list = new ArrayList<>();

        database = FirebaseDatabase.getInstance("https://pariwesata-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Destinasi");

        System.out.println("test: " + database);

        recycleAdapter = new RecycleAdapter(list, this.getContext());
        recyclerView.setAdapter(recycleAdapter);

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    try {
                        DataDestinasi pariwisata = dataSnapshot.getValue(DataDestinasi.class);
                        list.add(pariwisata);
                    } catch (Exception error) {
                        Toast.makeText(getActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
                    }
                }
                recycleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

