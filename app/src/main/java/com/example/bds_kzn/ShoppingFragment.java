package com.example.bds_kzn;

import android.content.pm.ShortcutInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        GetData();
        init(view);
        return(view);
    }

    public void init(View view){
        shoppingViewLeft = view.findViewById(R.id.shopping_page_recycler);
        shoppingViewLeft.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        shoppingViewLeft.setLayoutManager(layoutManager);

    }


    public void GetData(){
        ApiService apiService = new ApiService();

        Call<List<Shopping>> call = apiService.getShopping();

        call.enqueue(new Callback<List<Shopping>>() {
            @Override
            public void onResponse(Call<List<Shopping>> call, Response<List<Shopping>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Utility.setShoppingItems( response.body());
                    ShoppingPageRecyclerAdapter shoppingAdapter = new ShoppingPageRecyclerAdapter(Utility.getShoppingItems());
                    shoppingViewLeft.setAdapter(shoppingAdapter);
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<List<Shopping>> call, Throwable t) {
                // Handle network failure
            }
        });
    }
}
