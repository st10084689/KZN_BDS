package com.example.bds_kzn;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
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
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    private RecyclerView eventRecycler;
    private RecyclerView shoppingRecycler;

    private RelativeLayout mainContentBackground,donationBackgroundButton;



    Button donationButton;

    TextView eventButton, shoppingButton;

    Animation scaleUp, scaleDown;

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

//        //initialising the relative layout that contains the main context such as the recyclerviews
//        donationBackgroundButton = view.findViewById(R.id.donate_section_background);


        //initializing the buttons and the button and animations

        donationButton = view.findViewById(R.id.donation_button_background);
        eventButton = view.findViewById(R.id.event_see_all_txt);
        shoppingButton = view.findViewById(R.id.see_all_txt);

        scaleUp = AnimationUtils.loadAnimation(this.getContext(), R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this.getContext(), R.anim.scale_down);

        donationButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                        donationButton.startAnimation(scaleUp);
                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    donationButton.startAnimation(scaleDown);
                }
                return true;
            }
        });






    }





}