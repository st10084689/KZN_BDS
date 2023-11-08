package com.example.bds_kzn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class EventDetails extends AppCompatActivity {

    private TextView eventTitle, eventDescription, eventDate,eventTime;

    private ImageView eventImage;

    private String title, description, images,date,time;

    private RelativeLayout backPressedBtn;

    private static final String TAG = "EventDetails";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        init();
    }

    public void init(){
        Intent getPosition = getIntent();
        title = getPosition.getStringExtra("eventTitle");
        description = getPosition.getStringExtra("eventDescription");
        images = getPosition.getStringExtra("eventImages");
        date = getPosition.getStringExtra("eventDate");
        time = getPosition.getStringExtra("eventTime");

        backPressedBtn = findViewById(R.id.on_back_button);


        //setting the textViews
        eventTitle = findViewById(R.id.EventDetailsTitle);
        eventDescription = findViewById(R.id.EventDetailsDescription);
        eventDate = findViewById(R.id.event_date);
        eventTime = findViewById(R.id.event_time);


        eventImage = findViewById(R.id.event_image);

        Glide.with(eventImage)
                .load(images)
                .into(eventImage);

        eventTitle.setText(title);
        eventDescription.setText(description);
        eventDate.setText(date);
        eventTime.setText("at " + time);

        eventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventDetails.this, imageViewerAcitvity.class);
                intent.putExtra("IMAGE_PATH", images);
                startActivity(intent);
            }
        });

        backPressedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}