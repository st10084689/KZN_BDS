package com.example.bds_kzn;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private RecyclerView eventsRecycler;
    private RecyclerView shoppingRecycler;

    private ImageView eventsError, shoppingError;

    private List<Event> sortedEventList;

    private RelativeLayout mainContentBackground, donationBackgroundButton;


    Button donationButton;

    TextView eventButton, shoppingButton;

    Animation scaleUp, scaleDown;

    private ProgressBar eventsProg, shoppingProg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        GetEventData();
        GetShoppingData();
        init(view);
        return view;
    }

    private void init(View view) {

        Utility util = new Utility();
        //initializing the event recycler
        eventsRecycler = view.findViewById(R.id.events_recycler);

        eventsRecycler.setHasFixedSize(true);
        eventsRecycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));


        //initializing the event recycler
        shoppingRecycler = view.findViewById(R.id.shopping_recycler);

        shoppingRecycler.setHasFixedSize(true);
        shoppingRecycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        shoppingProg = view.findViewById(R.id.loading_progress_shopping);
        eventsProg = view.findViewById(R.id.loading_progress_events);
        eventsProg.setVisibility(View.VISIBLE);
        shoppingProg.setVisibility(View.VISIBLE);
//        //initialising the relative layout that contains the main context such as the recyclerviews
//        donationBackgroundButton = view.findViewById(R.id.donate_section_background);


        //initializing the buttons and the button and animations

         eventsError = view.findViewById(R.id.eventsError);
        shoppingError = view.findViewById(R.id.shoppingError);


        donationButton = view.findViewById(R.id.donation_button_background);
        eventButton = view.findViewById(R.id.event_see_all_txt);
        shoppingButton = view.findViewById(R.id.see_all_txt);

        scaleUp = AnimationUtils.loadAnimation(this.getContext(), R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this.getContext(), R.anim.scale_down);

        donationButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    donationButton.startAnimation(scaleUp);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    donationButton.startAnimation(scaleDown);
                }
                return false;
            }
        });

        scaleDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // This method is called when the animation starts.
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // This method is called when the animation ends.
                // Trigger your Intent here
                Intent toClass = new Intent(getContext(), DonationActivity.class);
                startActivity(toClass);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // This method is called if the animation repeats.
            }
        });


    }

//    public List<Event> GetFirstFive(List<Event> events) {
//
//        for (int i = 0; i <= 4; i++) {
//            events.add(Utility.getEventItems().get(i));
//        }
//        return events;
//    }


//    public static List<Event> sortByDateTime(List<Event> events) {
//        Collections.sort(events, new Comparator<Event>() {
//            @Override
//            public int compare(Event event1, Event event2) {
//                Date date1 = event1.getEventTime();
//                Date date2 = event2.getEventTime();
//                return date1.compareTo(date2);
//            }
//        });
//
//        return events;
//    }


    public void GetShoppingData(){
        ApiService apiService = new ApiService();

        Call<List<Shopping>> call = apiService.getShopping();

        call.enqueue(new Callback<List<Shopping>>() {
            @Override
            public void onResponse(Call<List<Shopping>> call, Response<List<Shopping>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Utility.setShoppingItems( response.body());
                    shoppingRecyclerAdapter shoppingAdapter = new shoppingRecyclerAdapter(Utility.getShoppingItems());
                    shoppingRecycler.setAdapter(shoppingAdapter);
                    shoppingProg.setVisibility(View.GONE);

                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<List<Shopping>> call, Throwable t) {
                shoppingError.setVisibility(View.VISIBLE);
                shoppingProg.setVisibility(View.GONE);
            }
        });
    }
    public void GetEventData(){
        ApiService apiService = new ApiService();

        Call<List<Event>> call = apiService.getEvents();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.isSuccessful() && response.body() != null) {
                   Utility.setEventItems(response.body());
                    sortedEventList = Utility.getEventItems();
                    EventsPageRecyclerAdapter EventAdapter = new EventsPageRecyclerAdapter(sortedEventList);
                    eventsRecycler.setAdapter(EventAdapter);
                    eventsProg.setVisibility(View.GONE);

//                    sortedEventList = sortByDateTime(Utility.getEventItems());
//                    sortedEventList = GetFirstFive(sortedEventList);
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                // Handle network failure
                Log.d(TAG, "onFailure: + failed");
                eventsError.setVisibility(View.VISIBLE);
                eventsProg.setVisibility(View.GONE);
            }
        });
    }
}


