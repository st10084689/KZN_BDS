package com.example.bds_kzn;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EventsFragment extends Fragment {
    private RecyclerView eventRecycler;


    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        init(view);
        return view;
    }

    public void init(View view){
        //initializing the recycler
        eventRecycler = view.findViewById(R.id.event_page_recycler);


    }
}