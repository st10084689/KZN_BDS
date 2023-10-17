package com.example.bds_kzn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

public class DonationPersonalInfo extends AppCompatActivity {
private TextView termsTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_personal_info);
        init();
    }

    public void init(){
        termsTxt = findViewById(R.id.termsTxt);

        termsTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTermsDialog();
            }
        });
    }


    public void showTermsDialog() {
        TermsDialogFragment dialogFragment = new TermsDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "terms_dialog");
    }

}