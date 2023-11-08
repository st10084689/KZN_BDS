package com.example.bds_kzn;

import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
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

        Resources resources = holder.itemView.getResources();
        boolean isTablet = resources.getBoolean(R.bool.is_tablet);
        int margin = resources.getDimensionPixelSize(
                isTablet ? R.dimen.tablet_margin : R.dimen.phone_margin
        );
        int marginBottom = resources.getDimensionPixelSize(
                isTablet ? R.dimen.tablet_margin_bottom : R.dimen.phone_margin_bottom
        );

        Log.d(TAG, "Is Tablet: " + isTablet);
        Log.d(TAG, "Selected Margin: " + margin);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.eventCard.getLayoutParams();
        layoutParams.setMargins(margin, 0, margin, marginBottom);
        holder.eventCard.setLayoutParams(layoutParams);

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



        holder.eventDate.setText(model.getEventDate());
        String imageUrl = Utility.getBaseUrl() + model.getImages();
        holder.eventCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toEventDetails = new Intent(view.getContext(), EventDetails.class);
                toEventDetails.putExtra("eventPosition", position);

                toEventDetails.putExtra("eventTitle", model.getTitle());
                toEventDetails.putExtra("eventImages", imageUrl);
                toEventDetails.putExtra("eventDescription",model.getDescription());
                toEventDetails.putExtra("eventDate",model.getEventDate());
                toEventDetails.putExtra("eventTime",model.getEventTime());
                view.getContext().startActivity(toEventDetails);
            }
        });


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
        private CardView eventCard;

        private TextView eventDate;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            eventTitle  = itemView.findViewById(R.id.event_title);
            eventDescripion = itemView.findViewById(R.id.event_description_txt);
            eventImage  = itemView.findViewById(R.id.event_image);
            eventDate = itemView.findViewById(R.id.eventDate);
            eventCard = (itemView).findViewById(R.id.event_cardview);


        }

    }
}

