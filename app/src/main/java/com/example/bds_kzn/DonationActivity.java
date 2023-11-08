package com.example.bds_kzn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
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

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DonationActivity extends AppCompatActivity {

    private AppCompatButton tenDonation, fiftyDonation, hundredDonation, twoHundredDonation, continueButton, selectedButton;
    private Animation scaleUp, scaleDown;
    private RelativeLayout onBackButton;
    private String selectedPayment;
    private EditText customAmountEditText;
    private AppCompatButton[] donationButtons;
    private String reference;

    private static final String TAG = "DonationActivity";
    private boolean editTextHasFocus = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
    reference = generateUniqueReference();
        init();
    }

    public void init() {
        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

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
                int paymentAmount;
                if (selectedPayment != null && !selectedPayment.isEmpty()) {
                    paymentAmount = Integer.parseInt(selectedPayment);
                    sendPaymentRequest(paymentAmount);
                } else if (customAmountEditText.getText() != null && !customAmountEditText.getText().toString().isEmpty()) {
                    paymentAmount = Integer.parseInt(customAmountEditText.getText().toString());
                    sendPaymentRequest(paymentAmount);
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


    private void sendPaymentRequest(int amount) {
        List<InvoicePayments.LineItem> lineItems = new ArrayList<>();
// Creates the requested object
        InvoicePayments request = new InvoicePayments(
                "kzn_bds_App",
                "ZAR",
                amount*100,
                "micros",
                "2023-11-03T13:30:48.9070611Z",
                "micros_129473",
                reference,
                "open"
        );

        // Converts the request object to JSON string
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(request);

        // Sends the request using OkHttp
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, jsonRequest);

        Request httpRequest = new Request.Builder()
                .url("https://api.zapper.com/business/api/v1/merchants/63493/sites/80735/invoices")
                .post(requestBody)
                .addHeader("Authorization","Bearer b19e9abc54aa46a2a56596746207368d" )
                .addHeader("x-api-key", "b19e9abc54aa46a2a56596746207368d")
                .addHeader("Accept", "text/plain")
                .addHeader("Representation-Type", "deeplink/zappercode/v6")
                .build();

        client.newCall(httpRequest).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    sendPaymentGet(responseBody);
                    Log.d(TAG, "onResponse: success: " +  responseBody);
                    Log.d(TAG, "onResponse: success: " +   response);

                } else {

                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure:Call  " + call + " exception" + e);
            }
        });
    }
    private void sendPaymentGet(String code) {

        if (code != null) {
            Uri zapperCodeUri = Uri.parse(code);
            if (zapperCodeUri != null) {
                Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, zapperCodeUri);
                startActivity(openUrlIntent);
                //Opens the Zapper app
                Intent openZapperIntent = getPackageManager().getLaunchIntentForPackage("com.zapper.android");
                if (openZapperIntent != null) {

                    startActivity(openZapperIntent);

                } else {

                }
            }
        }
    }


    public static String generateUniqueReference() {
        String randomUUID = UUID.randomUUID().toString();
        return randomUUID;
    }
}

