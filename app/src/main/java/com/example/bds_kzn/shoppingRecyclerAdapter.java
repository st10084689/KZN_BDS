package com.example.bds_kzn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;




public class shoppingRecyclerAdapter  extends RecyclerView.Adapter<shoppingRecyclerAdapter.ItemHolder> {

    private static final String TAG = "shoppingRecyclerAdapter";

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_recycler_card, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        private TextView shoppingTitle;
        private TextView shoppingDescripion;
        private ImageView shoppingImage;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            shoppingImage = itemView.findViewById(R.id.shopping_image);
            shoppingTitle = itemView.findViewById(R.id.shopping_title_txt);
            shoppingDescripion = itemView.findViewById(R.id.shopping_description_txt);


        }

    }
}