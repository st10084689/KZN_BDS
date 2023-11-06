package com.example.bds_kzn;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EventsFragment extends Fragment {
    private RecyclerView eventPageRecycler;
    private ImageView eventsError;
    
    private ProgressBar eventsProgressBar;

    private static final String TAG = "EventsFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        init(view);
        loadEventRecycler();
        return view;
    }

    public void init(View view){
        eventsError = view.findViewById(R.id.eventsError);
        eventsProgressBar = view.findViewById(R.id.loading_progress_events);
        
        
        eventsError.setVisibility(View.GONE);
        eventsProgressBar.setVisibility(View.VISIBLE);
        //initializing the recycler
        eventPageRecycler = view.findViewById(R.id.event_page_recycler);

        EventsPageRecyclerAdapter eventAdapter = new EventsPageRecyclerAdapter(Utility.getEventItems());
        eventPageRecycler.setAdapter(eventAdapter);
        eventPageRecycler.setHasFixedSize(true);
        eventPageRecycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
    }
    
    public void loadEventRecycler(){
        if(Utility.getEventItems().isEmpty()) {
            GetEventData();
        }else {
            EventsPageRecyclerAdapter eventAdapter = new EventsPageRecyclerAdapter(Utility.getEventItems());
            eventPageRecycler.setAdapter(eventAdapter);
            eventsProgressBar.setVisibility(View.GONE);
        }
    }

    public void GetEventData(){
        ApiService apiService = new ApiService();

        Call<List<Event>> call = apiService.getEvents();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Utility.setEventItems(response.body());
                    EventsPageRecyclerAdapter EventAdapter = new EventsPageRecyclerAdapter(Utility.getEventItems());
                    eventPageRecycler.setAdapter(EventAdapter);
                    eventsProgressBar.setVisibility(View.GONE);

//                    sortedEventList = sortByDateTime(Utility.getEventItems());
//                    sortedEventList = GetFirstFive(sortedEventList);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                // Handle network failure
                Log.d(TAG, "onFailure e: + failed");
                eventsError.setVisibility(View.VISIBLE);
                eventsProgressBar.setVisibility(View.GONE);
            }
        });
    }
}