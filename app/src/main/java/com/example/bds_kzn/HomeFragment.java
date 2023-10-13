package com.example.bds_kzn;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    private RecyclerView eventRecycler;
    private RecyclerView shoppingRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        //initializing the event recycler
        eventRecycler = view.findViewById(R.id.events_recycler);

        eventRecycler.setHasFixedSize(true);
        eventRecycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        eventsRecyclerAdapter eventAdapter = new eventsRecyclerAdapter();
        eventRecycler.setAdapter(eventAdapter);

        //initializing the event recycler
        shoppingRecycler = view.findViewById(R.id.shopping_recycler);

        shoppingRecycler.setHasFixedSize(true);
        shoppingRecycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        shoppingRecyclerAdapter shoppingAdapter = new shoppingRecyclerAdapter();

        shoppingRecycler.setAdapter(shoppingAdapter);

    }

}