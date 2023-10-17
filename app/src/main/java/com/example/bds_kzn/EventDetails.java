package com.example.bds_kzn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class EventDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        init();
    }

    public void init(){
        ViewPager viewPager = findViewById(R.id.imageViewPager);
        SliderAdapter adapter = new SliderAdapter(this);
        viewPager.setAdapter(adapter);
    }
}