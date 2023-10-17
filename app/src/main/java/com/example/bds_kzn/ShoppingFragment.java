package com.example.bds_kzn;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShoppingFragment extends Fragment {

    private RecyclerView shoppingViewLeft;


    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_shopping, container, false);

        init(view);
        return(view);
    }

    public void init(View view){
        shoppingViewLeft = view.findViewById(R.id.shopping_page_recycler);


        int smallItemHeight = getResources().getDimensionPixelSize(R.dimen.small_item_height);
        int largeItemHeight = getResources().getDimensionPixelSize(R.dimen.large_item_height);

        CustomItemDecoration itemDecoration = new CustomItemDecoration(smallItemHeight, largeItemHeight);
        shoppingViewLeft.addItemDecoration(itemDecoration);
        shoppingViewLeft.setHasFixedSize(true);


        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        shoppingViewLeft.setLayoutManager(layoutManager);
        ShoppingPageRecyclerAdapter shoppingAdapterOne = new ShoppingPageRecyclerAdapter(this.getContext());
        shoppingViewLeft.setAdapter(shoppingAdapterOne);




    }
}