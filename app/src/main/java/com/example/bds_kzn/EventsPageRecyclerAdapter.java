package com.example.bds_kzn;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class EventsPageRecyclerAdapter extends RecyclerView.Adapter<EventsPageRecyclerAdapter.ItemHolder> {

    private static final String TAG = "eventsRecyclerAdapter";

    private List<Event> events = new ArrayList<>();


    public EventsPageRecyclerAdapter(List<Event> _events) {
        events = _events;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_page_recycler_card, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

            Event model = events.get(position);

            holder.eventTitle.setText(model.getTitle());
        Log.d(TAG, "onBindViewHolder: event Title" + model.getTitle());

        String originalDescription = model.getDescription();
        String[] words = originalDescription.split("\\s+");

        StringBuilder truncatedDescription = new StringBuilder();
        int wordCount = 0;
        for (String word : words) {
            if (wordCount < 5) {
                truncatedDescription.append(word).append(" ");
                wordCount++;
            } else {
                break;
            }
        }


        String finalDescription = truncatedDescription.toString().trim();


        if (wordCount < words.length) {
            finalDescription += "...";
        }

            holder.eventDescripion.setText(finalDescription);



            String imageUrl = Utility.getBaseUrl() + model.getImages();
            Glide.with(holder.eventImage)
                    .load(imageUrl)
                    .centerCrop()
                    .into(holder.eventImage);

        Log.d(TAG, "onBindViewHolder: "+ imageUrl);
    }

    @Override
    public int getItemCount() {

        Log.d(TAG, "getItemCount: " + events.size());
     return  events.size();

    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        private TextView eventTitle;
        private TextView eventDescripion;
        private ImageView eventImage;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            eventTitle  = itemView.findViewById(R.id.event_title);
            eventDescripion = itemView.findViewById(R.id.event_description_txt);
            eventImage  = itemView.findViewById(R.id.event_image);


        }

    }
}

