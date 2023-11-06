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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DonationActivity extends AppCompatActivity {

    private AppCompatButton tenDonation, fiftyDonation, hundredDonation, twoHundredDonation, continueButton, selectedButton;
    private Animation scaleUp, scaleDown;
    private RelativeLayout onBackButton;
    private String selectedPayment;
    private EditText customAmountEditText;
    private AppCompatButton[] donationButtons;

    private static final String TAG = "DonationActivity";
    private boolean editTextHasFocus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        init();
    }

    public void init() {
        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        //sets the height of the button that the users click on to selects teh price
        tenDonation = findViewById(R.id.tenDonation);
        fiftyDonation = findViewById(R.id.FiftyDonation);
        hundredDonation = findViewById(R.id.HundredDonation);
        twoHundredDonation = findViewById(R.id.TwoHundredDonation);
        continueButton = findViewById(R.id.continueButton);
        onBackButton = findViewById(R.id.on_back_button);
        customAmountEditText = findViewById(R.id.customDonation);

        donationButtons = new AppCompatButton[] {// an array of the donation buttons
                tenDonation,
                fiftyDonation,
                hundredDonation,
                twoHundredDonation
        };

        onBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        for (AppCompatButton button : donationButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleButtonClick(button);
                }
            });
        }

        customAmountEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editTextHasFocus = true;
                    handleEditTextFocus();
                } else {
                    editTextHasFocus = false;
                }
            }
        });
        customAmountEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleEditTextFocus();
            }
        });

        continueButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    continueButton.startAnimation(scaleUp);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    continueButton.startAnimation(scaleDown);
                }
                return false;
            }
        });

        scaleDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent toClass = new Intent(DonationActivity.this, DonationPersonalInfo.class);

                if (selectedPayment != null && !selectedPayment.isEmpty()) {
                    toClass.putExtra("donationAmount", selectedPayment);
                    Log.d(TAG, "onAnimationEnd: from the button " + selectedPayment);
                    startActivity(toClass);
                } else if (customAmountEditText.getText() != null && !customAmountEditText.getText().toString().isEmpty()) {
                    toClass.putExtra("donationAmount", customAmountEditText.getText().toString());
                    Log.d(TAG, "onAnimationEnd: from the button " + customAmountEditText.getText().toString());
                    startActivity(toClass);
                } else {
                    Toast.makeText(DonationActivity.this, "Please select a donation", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    private void handleEditTextFocus() {
        customAmountEditText.setText("");

        for (AppCompatButton button : donationButtons) {
            button.setTextColor(ContextCompat.getColor(this, R.color.black));
            button.setBackground(ContextCompat.getDrawable(this, R.drawable.donation_option_background));
        }
        selectedPayment = "";
    }

    private void handleButtonClick(AppCompatButton clickedButton) {
        if (editTextHasFocus) {
            editTextHasFocus = false;
        }

        for (AppCompatButton button : donationButtons) {
            if (button == clickedButton) {
                selectedPayment = button.getText().toString();

                button.setTextColor(ContextCompat.getColor(this, R.color.white));
                button.setBackground(ContextCompat.getDrawable(this, R.drawable.is_selected_donation));
                switch (button.getText().toString()){
                    case "R10":
                        selectedPayment = "10";
                        break;
                    case "R50":
                        selectedPayment = "50";
                        break;
                    case "R100":
                        selectedPayment = "100";
                        break;
                    case "R200":
                        selectedPayment = "200";
                        break;

                }Log.d(TAG, "handleButtonClick: +"+ selectedPayment);
            } else {
                button.setTextColor(ContextCompat.getColor(this, R.color.black));
                button.setBackground(ContextCompat.getDrawable(this, R.drawable.donation_option_background));
            }
        }

        customAmountEditText.setText("");

    }
}
