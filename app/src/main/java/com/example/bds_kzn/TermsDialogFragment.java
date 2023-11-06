package com.example.bds_kzn;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class TermsDialogFragment extends DialogFragment {
Button dismissBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_pop_up_layout, container, false);
       dismissBtn = view.findViewById(R.id.dismissButton);

       dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // Closes the dialog
            }
        });
        return view;
    }



}
