package com.example.bds_kzn;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShoppingFragment extends Fragment {

    private RecyclerView shoppingViewLeft;
    private RecyclerView shoppingViewRight;

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

        shoppingViewLeft.setHasFixedSize(true);
        shoppingViewLeft.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        ShoppingPageRecyclerAdapter shoppingAdapterOne = new ShoppingPageRecyclerAdapter();
        shoppingAdapterOne.getRecyclerType(0);
        shoppingViewLeft.setAdapter(shoppingAdapterOne);

        shoppingViewRight = view.findViewById(R.id.shopping_page_recycler_second);

        shoppingViewRight.setHasFixedSize(true);
        shoppingViewRight.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        ShoppingPageRecyclerAdapter shoppingAdapterTwo = new ShoppingPageRecyclerAdapter();
        shoppingAdapterTwo.getRecyclerType(1);
        shoppingViewRight.setAdapter(shoppingAdapterTwo);
    }
}