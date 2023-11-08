package com.example.bds_kzn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class imageViewerAcitvity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer_acitvity);


        String imagePath = getIntent().getStringExtra("IMAGE_PATH");


        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(imageView)
                .load(imagePath)
                .into(imageView);
    }
}