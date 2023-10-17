package com.example.bds_kzn;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;



public class CustomItemDecoration extends RecyclerView.ItemDecoration {

    private int smallItemHeight;  // Height for small items
    private int largeItemHeight;  // Height for large items

    public CustomItemDecoration(int smallItemHeight, int largeItemHeight) {
        this.smallItemHeight = smallItemHeight;
        this.largeItemHeight = largeItemHeight;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);

        if (position % 3 == 0) {
            setItemViewHeight(view, largeItemHeight);
        } else {
            setItemViewHeight(view, smallItemHeight);
        }
    }

    private void setItemViewHeight(View view, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
        view.setLayoutParams(params);
    }
}
