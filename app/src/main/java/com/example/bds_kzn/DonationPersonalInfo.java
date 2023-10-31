package com.example.bds_kzn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DonationPersonalInfo extends AppCompatActivity {
private TextView termsTxt;

Animation scaleUp, scaleDown;

private AppCompatButton donateButton;

private RelativeLayout onBackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_personal_info);
        init();
    }

    public void init(){

        donateButton = findViewById(R.id.donateButton);
        onBackButton = findViewById(R.id.on_back_button);
        termsTxt = findViewById(R.id.termsTxt);

        termsTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTermsDialog();
            }
        });

        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        onBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        donateButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    donateButton.startAnimation(scaleUp);

                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    donateButton.startAnimation(scaleDown);
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


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
    }

    public void showTermsDialog() {
        TermsDialogFragment dialogFragment = new TermsDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "terms_dialog");
    }

}