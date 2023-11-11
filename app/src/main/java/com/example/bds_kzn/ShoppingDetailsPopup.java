package com.example.bds_kzn;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import java.util.List;


public class ShoppingDetailsPopup extends DialogFragment {
    private Product shoppingItems;
    private OnDialogDismissListener listener;
    RelativeLayout dismissButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_details_pop_up, container, false);
        dismissButton = view.findViewById(R.id.dismissButton);


        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // Close the dialog
            }
        });
        return view;
    }
    public interface OnDialogDismissListener {
        void onDismiss();
    }
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (listener != null) {
            listener.onDismiss();
        }
    }

    // Add a method to set the listener
    public void setOnDialogDismissListener(OnDialogDismissListener listener) {
        this.listener = listener;
    }

}
