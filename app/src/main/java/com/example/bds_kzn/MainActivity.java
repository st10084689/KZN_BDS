package com.example.bds_kzn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout homeBtn;
    private RelativeLayout shoppingBtn;
    private RelativeLayout eventsBtn;
    private RelativeLayout aboutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    public void init(){
        //initializing the buttons
        homeBtn = findViewById(R.id.home_relative);
        shoppingBtn = findViewById(R.id.shopping_relative);
        eventsBtn = findViewById(R.id.event_relative);
        aboutBtn = findViewById(R.id.about_relative);

        homeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loadFragment(new HomeFragment());
            }
        });

        shoppingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ShoppingFragment());
            }
        });

        eventsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new EventsFragment());
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new AboutUsFragment());
            }
        });

        // Load initial fragment (HomeFragment)
        loadFragment(new HomeFragment());
    }




    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_fragment, fragment);
        fragmentTransaction.addToBackStack(null); // Optional, allows the user to navigate back
        fragmentTransaction.commit();
    }



}