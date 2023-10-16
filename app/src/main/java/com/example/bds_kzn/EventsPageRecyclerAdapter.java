package com.example.bds_kzn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventsPageRecyclerAdapter extends RecyclerView.Adapter<EventsPageRecyclerAdapter.ItemHolder> {

    private static final String TAG = "eventsRecyclerAdapter";

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_page_recycler_card, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
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

