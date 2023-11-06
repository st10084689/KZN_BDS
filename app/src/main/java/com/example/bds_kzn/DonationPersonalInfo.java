package com.example.bds_kzn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DonationPersonalInfo extends AppCompatActivity {
private TextView termsTxt;

Animation scaleUp, scaleDown;

private CheckBox IsDonationAnon;

private AppCompatButton donateButton;

private EditText username, surname, email;

private Spinner title;

private RelativeLayout onBackButton;

private int amount;

private Properties properties;

private String reference;

private CheckBox agreeToTerms;

    private static final String TAG = "DonationPersonalInfo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_personal_info);
        reference = generateUniqueReference();
        Log.d(TAG, "onCreate: "+reference);
        Intent intent = getIntent();
        amount = Integer.valueOf(intent.getStringExtra("donationAmount"));
        init();

    }

    public void init(){
        donateButton = findViewById(R.id.donateButton);
        onBackButton = findViewById(R.id.on_back_button);
        termsTxt = findViewById(R.id.termsTxt);

        IsDonationAnon = findViewById(R.id.IsAnonymousCheckBox);

        title = findViewById(R.id.NameSpinner);
        username = findViewById(R.id.Name);
        surname = findViewById(R.id.surname);
        email = findViewById(R.id.Email);

        agreeToTerms = findViewById(R.id.IsTermsCheckBox);


        IsDonationAnon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                 title.setVisibility(!isChecked ? View.VISIBLE: View.GONE);
                 username.setVisibility(!isChecked ? View.VISIBLE: View.GONE);
                 surname.setVisibility(!isChecked ? View.VISIBLE: View.GONE);
                 email.setVisibility(!isChecked ? View.VISIBLE: View.GONE);
            }
        });

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

                boolean isValid = validateFields();
                boolean isTermsChecked = agreeToTerms.isChecked();

                if (isValid && isTermsChecked) {
                    sendPaymentRequest();
                } else {
                    if (!isTermsChecked) {
                        Toast.makeText(DonationPersonalInfo.this, "You must agree to the terms", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
    }

    private boolean validateFields() {
        String titleText = title.getSelectedItem().toString();
        String usernameText = username.getText().toString().trim();
        String surnameText = surname.getText().toString().trim();
        String emailText = email.getText().toString().trim();

        boolean isValid = true;

        // Validates title
        if (titleText.isEmpty() && !IsDonationAnon.isChecked()) {
            Toast.makeText(this, "Title is required", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        // Validates username
        if (usernameText.isEmpty() && !IsDonationAnon.isChecked()) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        // Validates surname
        if (surnameText.isEmpty() && !IsDonationAnon.isChecked()) {
            Toast.makeText(this, "Surname is required", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        // Validates email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches() && !IsDonationAnon.isChecked()) {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }


    public void showTermsDialog() {
        TermsDialogFragment dialogFragment = new TermsDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "terms_dialog");
    }

    private void sendPaymentRequest() {
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
                "open",
                "John",
                "Doe"
        );

       properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("local.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        String authorizationKey = BuildConfig.apiKey;
        String apiKey=  properties.getProperty("apiKey");

        Log.d(TAG, "sendPaymentRequest: "+apiKey);

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