package com.example.bds_kzn;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingPageRecyclerAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "shoppingRecyclerPageAdapter";
    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int VIEW_TYPE_SMALL = 1;

    private int largeItemHeight = 400;

    private int smallItemHeight = 300;


    private Context context;

    public ShoppingPageRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        // Set the height of the view here based on the position
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_page_recycler_card, parent, false);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.height = largeItemHeight;
            view.setLayoutParams(params);
            return new LargeItemHolder(view);
        } else {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_recycling_card_small, parent, false);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.height = smallItemHeight;
            view.setLayoutParams(params);
            return new NormalItemHolder(view);
        }


    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public int getItemViewType(int position) {
        return position % 3 == 0 ? VIEW_TYPE_SMALL : VIEW_TYPE_NORMAL;
    }


    @Override
    public int getItemCount() {
        return 12;
    }

    public class NormalItemHolder extends RecyclerView.ViewHolder {

        private TextView shoppingTitle;
        private TextView shoppingDescripion;
        private ImageView shoppingImage;

        public NormalItemHolder(@NonNull View itemView) {
            super(itemView);
            shoppingImage = itemView.findViewById(R.id.statement_badge);
            shoppingTitle = itemView.findViewById(R.id.statement_title);
            shoppingDescripion = itemView.findViewById(R.id.shopping_description_txt);
        }
    }

    public class LargeItemHolder extends RecyclerView.ViewHolder {

        private TextView shoppingTitle;
        private TextView shoppingDescripion;
        private ImageView shoppingImage;

        public LargeItemHolder(@NonNull View itemView) {
            super(itemView);
            shoppingImage = itemView.findViewById(R.id.statement_badge);
            shoppingTitle = itemView.findViewById(R.id.statement_title);
            shoppingDescripion = itemView.findViewById(R.id.shopping_description_txt);
        }
    }


}
