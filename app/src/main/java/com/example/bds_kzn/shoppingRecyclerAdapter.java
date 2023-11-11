package com.example.bds_kzn;

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

import java.util.List;


public class shoppingRecyclerAdapter  extends RecyclerView.Adapter<shoppingRecyclerAdapter.ItemHolder> {

    private static final String TAG = "shoppingRecyclerAdapter";

    private List<Product> shopItems;

    public shoppingRecyclerAdapter(List<Product> shopItems) {
        this.shopItems = shopItems;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_recycler_card, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        //if statement to make the last item in the list to have a margin of 15dp . change out 6 to the dataset size
        if (position == shopItems.size() - 1) {

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            params.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, holder.itemView.getResources().getDisplayMetrics());
            holder.itemView.setLayoutParams(params);
        }

        Product model = shopItems.get(position);

        holder.shoppingTitle.setText(model.getTitle());
        Log.d(TAG, "onBindViewHolder: shopping Title" + model.getTitle());
        double price = Double.parseDouble(model.getPrice());
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

        Glide.with(holder.shoppingImage)
                .load(model.getImage())
                .centerCrop()
                .into(holder.shoppingImage);



//        Log.d(TAG, "onBindViewHolder: "+ imageUrl);


    }

    @Override
    public int getItemCount() {
        if(shopItems.size() > 3){
        return 5;}
        else{
            return shopItems.size();
        }
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        private TextView shoppingTitle;
        private TextView shoppingPrice;
        private ImageView shoppingImage;

        private CardView shoppingCard;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            shoppingImage = itemView.findViewById(R.id.shopping_image);
            shoppingTitle = itemView.findViewById(R.id.shopping_title);
            shoppingPrice = itemView.findViewById(R.id.shopping_description_txt);
            shoppingCard = itemView.findViewById(R.id.ShoppingCard);

        }

    }
}