package com.example.bds_kzn;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class eventsRecyclerAdapter extends RecyclerView.Adapter<eventsRecyclerAdapter.ItemHolder> {

    private static final String TAG = "eventsRecyclerAdapter";

    private List<Integer> departments;

    public eventsRecyclerAdapter(List<Integer> departments) {
        this.departments = departments;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.departments_layoutcard, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        //if statement to make the last item in the list to have a margin of 15dp . change out 3 to the dataset size
//        if (position == departments.size() - 1) {
//
//            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
//            params.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, holder.itemView.getResources().getDisplayMetrics());
//            holder.itemView.setLayoutParams(params);
//        }

        Glide.with(holder.department)
                .load(departments.get(position))
                .centerCrop()
                .into(holder.department);
    }

    @Override
    public int getItemCount() {
        return departments.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{


        private ImageView department;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            department = itemView.findViewById(R.id.department_image);


        }

    }
}
