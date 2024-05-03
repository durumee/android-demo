package com.nrzm.retrofitlocaldemo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListAdapter adapter;
    private List<FoodNutrition.FoodNutrtionInfo.FoodDetail> foodDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recView = findViewById(R.id.recView);
        adapter = new ListAdapter(foodDetails);

        recView.setAdapter(adapter);
//        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setLayoutManager(new GridLayoutManager(this, 2));

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<FoodNutrition> call = apiService.foodList();

        call.enqueue(new Callback<FoodNutrition>() {
            @Override
            public void onResponse(Call<FoodNutrition> call, Response<FoodNutrition> response) {
                if (response.isSuccessful()) {
                    FoodNutrition foodNutrition = response.body();
                    foodDetails = foodNutrition.getFoodNutrtionInfo().getFoodDetails();

                    Log.i("내용확인", foodNutrition.getFoodNutrtionInfo().getResultMsg().get("MSG"));
                    Log.i("내용확인", foodDetails.get(0).getMakerName());
                    Log.i("내용확인", foodDetails.get(0).getDescKor());
                    adapter.updateList(foodDetails);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<FoodNutrition> call, Throwable t) {
                Log.i("내용확인", "실패" + t.getMessage());

            }
        });
    }
}