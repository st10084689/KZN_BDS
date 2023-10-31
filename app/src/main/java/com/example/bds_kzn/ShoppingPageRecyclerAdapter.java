package com.example.bds_kzn;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ShoppingPageRecyclerAdapter extends RecyclerView.Adapter<ShoppingPageRecyclerAdapter.ItemHolder> {

    private static final String TAG = "shoppingRecyclerAdapter";
    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int VIEW_TYPE_SMALL = 1;

    List<Shopping> shop = new ArrayList<>();

    private int largeItemHeight = 400;
    private int smallItemHeight = 350;


    private Context context;

    public ShoppingPageRecyclerAdapter(List<Shopping> _shop) {
        shop = _shop;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_page_recycler_card, parent, false);
            return new ItemHolder(view);
        }
        else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_recycling_card_small, parent, false);
            return new ItemHolder(view);
        }

    }





    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Shopping model = shop.get(position);

        holder.shoppingTitle.setText(model.getTitle());
        Log.d(TAG, "onBindViewHolder: shopping Title" + model.getTitle());
        double price = model.getPrice();
        String priceAsString = "R" + price;
        holder.shoppingPrice.setText(priceAsString);
        Log.d(TAG, "onBindViewHolder: "+model.getPrice());

        holder.shoppingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toShoppingDetails = new Intent(view.getContext(), shopping_details.class);
                toShoppingDetails.putExtra("Position",position);
                view.getContext().startActivity(toShoppingDetails);
            }
        });

        String imageUrl = Utility.getBaseUrl() + model.getImages();
        Glide.with(holder.shoppingImage)
                .load(imageUrl)
                .centerCrop()
                .into(holder.shoppingImage);



        Log.d(TAG, "onBindViewHolder: "+ imageUrl);

    }



    @Override
    public int getItemViewType(int position) {
        return position % 3 == 0 ? VIEW_TYPE_SMALL : VIEW_TYPE_NORMAL;
    }

//    private int convertPixelsToDp(int px) {
//        Log.d(TAG, "convertPixelsToDp: "+ px);
//        Log.d(TAG, "convertPixelsToDp: + "+ context.getResources().getDisplayMetrics().density);
//        float density = context.getResources().getDisplayMetrics().density;
//        Log.d(TAG, "convertPixelsToDp: "+ Math.round((px / density)));
//        return Math.round(px / density);
//
//    }


    @Override
    public int getItemCount() {
        return shop.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private CardView shoppingCard;
        private TextView shoppingTitle;
        private TextView shoppingPrice;
        private ImageView shoppingImage;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            shoppingCard = itemView.findViewById(R.id.CardLayoutItem);
            shoppingImage = itemView.findViewById(R.id.shopping_image);
            shoppingTitle = itemView.findViewById(R.id.shopping_title);
            shoppingPrice = itemView.findViewById(R.id.shopping_description_txt);
        }
    }
}