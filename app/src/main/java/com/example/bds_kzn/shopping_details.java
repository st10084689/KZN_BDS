package com.example.bds_kzn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class shopping_details extends AppCompatActivity implements ShoppingDetailsPopup.OnDialogDismissListener {
private TextView shoppingTitle;
private TextView shoppingDescription;
private TextView priceTxt;

private ImageView shoppingImage;

private Shopping shopItem;

private int position;
private RelativeLayout backPressedBtn;

private Button purchaseButton;

    Animation scaleUp, scaleDown;

    private static final String TAG = "shopping_details";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_details);
        Intent intent = getIntent();
         position = intent.getIntExtra("Position", 1);
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

        purchaseButton = findViewById(R.id.purchaseBtn);

        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        purchaseButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    purchaseButton.startAnimation(scaleUp);

                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    purchaseButton.startAnimation(scaleDown);
                }

                return false;
            }
        });

        scaleDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // This method is called when the animation starts.
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showTextNotice();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // This method is called if the animation repeats.
            }
        });



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
                .into(shoppingImage);
    }

    public void sendEmail() {
        Intent toEmail = new Intent(Intent.ACTION_SEND);
        toEmail.setType("message/rfc822");
        toEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"admin3@bdskzn.co.za"});
        toEmail.putExtra(Intent.EXTRA_SUBJECT, "purchase information for " + shopItem.getTitle());
        toEmail.putExtra(Intent.EXTRA_TEXT, "Hi, I'd like to purchase " + shopItem.getTitle() + " for R" + shopItem.getPrice());

        startActivity(Intent.createChooser(toEmail, "Send Email"));
    }


    public void showTextNotice() {
         ShoppingDetailsPopup sendPopup= new ShoppingDetailsPopup();

        sendPopup.setOnDialogDismissListener(new ShoppingDetailsPopup.OnDialogDismissListener() {
            @Override
            public void onDismiss() {
                sendEmail();
            }
        });
        sendPopup.show(getSupportFragmentManager(), "terms_dialog");

    }

    @Override
    public void onDismiss() {

    }
}