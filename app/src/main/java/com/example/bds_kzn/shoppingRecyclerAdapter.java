package com.example.bds_kzn;

import android.util.TypedValue;
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
        //if statement to make the last item in the list to have a margin of 15dp . change out 6 to the dataset size
        if (position == 6 - 1) {

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            params.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, holder.itemView.getResources().getDisplayMetrics());
            holder.itemView.setLayoutParams(params);
        }

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
            shoppingImage = itemView.findViewById(R.id.statement_badge);
            shoppingTitle = itemView.findViewById(R.id.statement_title);
            shoppingDescripion = itemView.findViewById(R.id.shopping_description_txt);


        }

    }
}