package com.example.bds_kzn;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EventsFragment extends Fragment {
    private RecyclerView eventPageRecycler;

    private CardView mainContentCard;

    private List<Event> events;



    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        GetData();
        init(view);
        return view;
    }

    public void init(View view){
        //initializing the recycler
        eventPageRecycler = view.findViewById(R.id.event_page_recycler);

        eventPageRecycler.setHasFixedSize(true);
        eventPageRecycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    public void GetData(){
        ApiService apiService = new ApiService();

        Call<List<Event>> call = apiService.getEvents();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Event> events = response.body();
                    EventsPageRecyclerAdapter eventAdapter = new EventsPageRecyclerAdapter(events);
                    eventPageRecycler.setAdapter(eventAdapter);
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                // Handle network failure
            }
        });
    }
}