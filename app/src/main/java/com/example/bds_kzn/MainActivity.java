package com.example.bds_kzn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView homeIcon,shoppingIcon,eventIcon,aboutIcon;

    private ImageView homeUnderLine, shoppingUnderline, eventUnderline, aboutUnderline,chosenUnderline;

    private TextView homeText,shoppingText, eventText, aboutText;


    private RelativeLayout homeBtn,shoppingBtn,eventsBtn,aboutBtn;

    private static final String TAG = "MainActivity";

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

        //initialising the imageViews
        homeIcon = findViewById(R.id.home_icon);
        shoppingIcon = findViewById(R.id.shopping_icon);
        eventIcon = findViewById(R.id.event_icon);
        aboutIcon = findViewById(R.id.about_icon);

        //initialising the textViews
        homeText = findViewById(R.id.home_nav_txt);
        shoppingText = findViewById(R.id.shopping_nav_txt);
        eventText = findViewById(R.id.event_nav_txt);
        aboutText = findViewById(R.id.about_nav_txt);

        //initialising the underlines imageViews
        homeUnderLine = findViewById(R.id.home_underline);
        shoppingUnderline = findViewById(R.id.shopping_underline);
        eventUnderline = findViewById(R.id.event_underline);
        aboutUnderline = findViewById(R.id.about_underline);
        chosenUnderline = homeUnderLine;



        homeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                changeNavBarColors(R.drawable.home_icon_selected, homeIcon,homeText,homeUnderLine);
                animateUnderline(chosenUnderline,homeUnderLine);
                chosenUnderline = homeUnderLine;
                loadFragment(new HomeFragment());
            }
        });

        shoppingBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                changeNavBarColors(R.drawable.shopping_icon_selected, shoppingIcon,shoppingText,shoppingUnderline);
                animateUnderline(chosenUnderline,shoppingUnderline);
                chosenUnderline = shoppingUnderline;
                loadFragment(new ShoppingFragment());
            }
        });

        eventsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNavBarColors(R.drawable.events_icon_selected, eventIcon, eventText,eventUnderline);
                animateUnderline(chosenUnderline,eventUnderline);
                chosenUnderline = eventUnderline;
                loadFragment(new EventsFragment());
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeNavBarColors(R.drawable.account_icon_selected, aboutIcon,aboutText,aboutUnderline);
                animateUnderline(chosenUnderline,aboutUnderline);
                chosenUnderline = aboutUnderline;
                loadFragment(new AboutUsFragment());
            }
        });

        // Load initial fragment and colors (HomeFragment)
        changeNavBarColors(R.drawable.home_icon_selected, homeIcon,homeText,homeUnderLine);
        loadFragment(new HomeFragment());
    }




    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_fragment, fragment);
        fragmentTransaction.addToBackStack(null); // Optional, allows the user to navigate back
        fragmentTransaction.commit();
    }

    private void changeNavBarColors(int drawableValue, ImageView image,TextView text, ImageView underLine){
        homeIcon.setImageResource(R.drawable.home_icon);
        shoppingIcon.setImageResource(R.drawable.shopping_bag_icon);
        aboutIcon.setImageResource(R.drawable.about_us_icon);
        eventIcon.setImageResource(R.drawable.events_icon);

        int baseColor = R.color.grey;
        int orange = R.color.orange;

        //setting all the textViews to have a base color
        homeText.setTextColor(getResources().getColor(baseColor));
        shoppingText.setTextColor(getResources().getColor(baseColor));
        aboutText.setTextColor(getResources().getColor(baseColor));
        eventText.setTextColor(getResources().getColor(baseColor));


        //setting all the under to not disappear
        homeUnderLine.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        shoppingUnderline.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        eventUnderline.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        aboutUnderline.setBackgroundColor(Color.parseColor("#00FFFFFF"));

            image.setImageResource(drawableValue);//changing the selected icons drawable to their _selected variant
            underLine.setBackgroundResource(R.drawable.chosen_underline);

            text.setTextColor(getResources().getColor(orange));//changing the textcolor of the relevent textView

        }

    private void animateUnderline( ImageView chosenUnderline,ImageView targetUnderline) {


        int[] startPosition = new int[2];
        int[] endPosition = new int[2];

        chosenUnderline.getLocationInWindow(startPosition);
        targetUnderline.getLocationInWindow(endPosition);

            TranslateAnimation animation = new TranslateAnimation(
                startPosition[0] - endPosition[0], 0,
                startPosition[1] - endPosition[1], 0
        );

        Log.d("Animation", "Start position: (" + startPosition[0] + ", " + startPosition[1] + ")");
        Log.d("Animation", "End position: (" + endPosition[0] + ", " + endPosition[1] + ")");

        Log.d("Animation", "TranslationX: " + (endPosition[0] - startPosition[0]) + ", TranslationY: " + (endPosition[1] - startPosition[1]));


        animation.setDuration(500);
        animation.setFillAfter(true);

        targetUnderline.startAnimation(animation);
    }

    }



