package com.example.bds_kzn;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
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
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private List<Event> sortedEventList = new ArrayList<>();
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
       //initialising the util class
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

         eventsError = view.findViewById(R.id.eventsError);
        shoppingError = view.findViewById(R.id.shoppingError);


        donationButton = view.findViewById(R.id.donation_button_background);
        eventButton = view.findViewById(R.id.event_see_all_txt);
        shoppingButton = view.findViewById(R.id.see_all_txt);

        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment eventsFragment = new EventsFragment();
                ((MainActivity) getActivity()).loadFragment(eventsFragment);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity homeClass = (MainActivity) getActivity();
                            homeClass.changeNavBarColors(R.drawable.events_icon_selected, homeClass.eventIcon, homeClass.eventText,homeClass.eventUnderline);
                            homeClass.animateUnderline(homeClass.chosenUnderline,homeClass.eventUnderline);
                            homeClass.chosenUnderline = homeClass.eventUnderline;
                        }
                    }, 250);
            }
        });

        shoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment shoppingFragment = new ShoppingFragment();
                ((MainActivity) getActivity()).loadFragment(shoppingFragment);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity homeClass = (MainActivity) getActivity();
                        homeClass.changeNavBarColors(R.drawable.shopping_icon_selected, homeClass.shoppingIcon, homeClass.shoppingText,homeClass.shoppingUnderline);
                        homeClass.animateUnderline(homeClass.chosenUnderline,homeClass.shoppingUnderline);
                        homeClass.chosenUnderline = homeClass.shoppingUnderline;
                    }
                }, 250);
            }
        });

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
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent toClass = new Intent(getContext(), DonationActivity.class);
                startActivity(toClass);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });


    }

    public static Event findClosestEvent(List<Event> events) {
        Date closestDate = null;
        Event closestEvent = null;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date presentDate = new Date();

        for (Event event : events) {
            try {
                Date eventDate = dateFormat.parse(event.getDate());

                if (closestDate == null || Math.abs(eventDate.getTime() - presentDate.getTime()) < Math.abs(closestDate.getTime() - presentDate.getTime())) {
                    closestDate = eventDate;
                    closestEvent = event;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return closestEvent;
    }

    public void GetShoppingData(){
        ApiService apiService = new ApiService();

        Call<shoppingResponse> call = apiService.getShopping();

        call.enqueue(new Callback<shoppingResponse> () {
            @Override
            public void onResponse(Call<shoppingResponse>  call, Response<shoppingResponse>  response) {
                if (response.isSuccessful() && response.body() != null) {
                    Utility.setShoppingItems(response.body().getProducts());
                    shoppingRecyclerAdapter shoppingAdapter = new shoppingRecyclerAdapter(Utility.getShoppingItems());
                    shoppingRecycler.setAdapter(shoppingAdapter);
                    shoppingProg.setVisibility(View.GONE);

                } else {

                }
            }

            @Override
            public void onFailure(Call<shoppingResponse>  call, Throwable t) {
                shoppingError.setVisibility(View.VISIBLE);
                shoppingProg.setVisibility(View.GONE);

            }
        });
    }
    public void GetEventData(){
        ApiService apiService = new ApiService();

        Call<eventsResponse> call = apiService.getEvents();

        call.enqueue(new Callback<eventsResponse>() {
            @Override
            public void onResponse(Call<eventsResponse> call, Response<eventsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Utility.setEventItems(response.body().getEvents());
                    sortedEventList.add(findClosestEvent(Utility.getEventItems()));
                    EventsPageRecyclerAdapter EventAdapter = new EventsPageRecyclerAdapter(sortedEventList);
                    eventsRecycler.setAdapter(EventAdapter);
                    eventsProg.setVisibility(View.GONE);
                } else {

                }
            }

            @Override
            public void onFailure(Call<eventsResponse> call, Throwable t) {
                // Handles network failure
                Log.d(TAG, "onFailure: + failed"+ t +" call: "+ call);
                eventsError.setVisibility(View.VISIBLE);
                eventsProg.setVisibility(View.GONE);
            }
        });
    }

}


