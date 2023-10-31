package com.example.bds_kzn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class shopping_details extends AppCompatActivity {
private TextView shoppingTitle;
private TextView shoppingDescription;
private TextView priceTxt;

private ImageView shoppingImage;

private Shopping shopItem;

private int position;
private RelativeLayout backPressedBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_details);
        Intent intent = getIntent();
         position = intent.getIntExtra("Position", -1);
         init();


    }

    public void init(){
        shoppingTitle = findViewById(R.id.shoppingDetailsTitle);
        shoppingDescription = findViewById(R.id.shoppingDetailsDescription);
        priceTxt = findViewById(R.id.price);
        shoppingImage = findViewById(R.id.imageShopping);

        shopItem = Utility.getShoppingItems().get(position);

        shoppingTitle.setText(shopItem.getTitle());

        backPressedBtn = findViewById(R.id.on_back_button);

        priceTxt.setText("R"+ shopItem.getPrice().toString());

        backPressedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        shoppingDescription.setText(shopItem.getDescription());
        String ImageUrl = Utility.getBaseUrl() + shopItem.getImages();
        Glide.with(shoppingImage)
                .load(ImageUrl)
                .centerCrop()
                .into(shoppingImage);
    }


}