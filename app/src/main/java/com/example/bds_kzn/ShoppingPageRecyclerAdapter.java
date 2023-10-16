package com.example.bds_kzn;

import android.util.Log;
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

    private static final int RECYCLER_ONE = 0;
    private static final int RECYCLER_TWO = 1;

    private int typeRecycler;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SMALL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_recycling_card_small, parent, false);
            return new LargeItemHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_page_recycler_card, parent, false);
            return new NormalItemHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {

        Log.d(TAG, "getItemViewType: Type Recycler"+ typeRecycler+"\n"+
                "position: "+ position);
        if (typeRecycler == RECYCLER_ONE) {
            Log.d(TAG, "getItemView: recycler one position: "+ position % 3);
            return position % 3 == 0 ? VIEW_TYPE_SMALL : VIEW_TYPE_NORMAL;
        }
        if (typeRecycler == RECYCLER_TWO) {
            Log.d(TAG, "getItemView: recycler two position: "+ position % 3);
                if(position % 3 == 1/3 || position % 4 == 0) {
                    return VIEW_TYPE_SMALL ;
                }

        }
        return VIEW_TYPE_NORMAL;
    }


    public void getRecyclerType(int typeRecycler){
        typeRecycler = typeRecycler;
    }

    @Override
    public int getItemCount() {
        return 6;
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
