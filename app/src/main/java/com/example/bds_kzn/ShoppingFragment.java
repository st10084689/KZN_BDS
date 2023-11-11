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
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingFragment extends Fragment {

    private RecyclerView shoppingViewLeft;

    private ImageView shoppingError;

    private ProgressBar shoppingProg;


    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_shopping, container, false);

        init(view);
        loadShoppingRecycler();
        return(view);
    }

    public void init(View view){
        shoppingProg = view.findViewById(R.id.loading_progress_shopping);
        shoppingError= view.findViewById(R.id.shoppingError);

        shoppingProg.setVisibility(View.VISIBLE);
                shoppingError.setVisibility(View.GONE);

        shoppingViewLeft = view.findViewById(R.id.shopping_page_recycler);
        shoppingViewLeft.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        shoppingViewLeft.setLayoutManager(layoutManager);

    }

    public void loadShoppingRecycler(){

        if(Utility.getShoppingItems().isEmpty()) {
            GetData();
        }else {
            ShoppingPageRecyclerAdapter shoppingAdapter = new ShoppingPageRecyclerAdapter(Utility.getShoppingItems());
            shoppingViewLeft.setAdapter(shoppingAdapter);
            shoppingProg.setVisibility(View.GONE);
        }
    }



    public void GetData(){
        ApiService apiService = new ApiService();

        Call<shoppingResponse> call = apiService.getShopping();

        call.enqueue(new Callback<shoppingResponse>() {
            @Override
            public void onResponse(Call<shoppingResponse> call, Response<shoppingResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Utility.setShoppingItems( response.body().getProducts());
                    ShoppingPageRecyclerAdapter shoppingAdapter = new ShoppingPageRecyclerAdapter(Utility.getShoppingItems());
                    shoppingViewLeft.setAdapter(shoppingAdapter);
                    shoppingProg.setVisibility(View.GONE);
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<shoppingResponse> call, Throwable t) {
                shoppingProg.setVisibility(View.GONE);
                shoppingError.setVisibility(View.VISIBLE);
            }
        });
    }
}
