package com.example.bds_kzn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class DonationActivity extends AppCompatActivity {

    private AppCompatButton tenDonation, fiftyDonation, hundredDonation, twoHundredDonation, continueButton, selectedButton;

    Animation scaleUp, scaleDown;

    private static final String TAG = "DonationActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        init();
    }

    public void init(){

        scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);


        tenDonation = findViewById(R.id.tenDonation);
        fiftyDonation = findViewById(R.id.FiftyDonation);
        hundredDonation = findViewById(R.id.HundredDonation);
        twoHundredDonation = findViewById(R.id.TwoHundredDonation);
        continueButton = findViewById(R.id.continueButton);

        tenDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(tenDonation);
            }
        });

        fiftyDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(fiftyDonation);
            }
        });

        hundredDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(hundredDonation);
            }
        });

        twoHundredDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(twoHundredDonation);
            }
        });
        continueButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    continueButton.startAnimation(scaleUp);
                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    continueButton.startAnimation(scaleDown);
                }

                return false;
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: entered the donation");
                Intent toClass = new Intent(DonationActivity.this, DonationPersonalInfo.class);
                startActivity(toClass);
            }
        });
    }

    private void handleButtonClick(AppCompatButton clickedButton) {
        if (selectedButton != null) {
            // Reset previously selected button
            selectedButton.setTextColor(ContextCompat.getColor(this, R.color.black)); // Set the default text color
            selectedButton.setBackground(ContextCompat.getDrawable(this, R.drawable.donation_option_background)); // Set the default background drawable
        }

        // Set the new selected button
        clickedButton.setTextColor(ContextCompat.getColor(this, R.color.white)); // Set text color to white
        clickedButton.setBackground(ContextCompat.getDrawable(this, R.drawable.is_selected_donation)); // Set the background drawable for selected state

        selectedButton = clickedButton;
    }
}