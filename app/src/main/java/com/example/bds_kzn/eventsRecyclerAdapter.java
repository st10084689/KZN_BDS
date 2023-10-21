package com.example.bds_kzn;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class eventsRecyclerAdapter extends RecyclerView.Adapter<eventsRecyclerAdapter.ItemHolder> {

    private static final String TAG = "eventsRecyclerAdapter";

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_recycler_card, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        //if statement to make the last item in the list to have a margin of 15dp . change out 3 to the dataset size
        if (position == 3 - 1) {

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            params.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, holder.itemView.getResources().getDisplayMetrics());
            holder.itemView.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return 3;//placeholder
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        private TextView eventTitle;
        private TextView eventDescripion;
        private ImageView eventImage;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            eventImage = itemView.findViewById(R.id.statement_badge);
            eventTitle = itemView.findViewById(R.id.statement_title);
            eventDescripion = itemView.findViewById(R.id.shopping_description_txt);


        }

    }
}
