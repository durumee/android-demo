package com.nrzm.exspinner;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView textViewSelected;
    private SpinnerAdapter adapter;

    private List<ItemData> itemList = Arrays.asList(
            new ItemData("Item 1"),
            new ItemData("Item 2"),
            new ItemData("Item 3"),
            new ItemData("Item 4"),
            new ItemData("Item 5")
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        textViewSelected = findViewById(R.id.textViewSelected);

        adapter = new SpinnerAdapter(itemList, item -> {
            //아이템 선택시 콜백 구간
            textViewSelected.setText(item.getText());
            recyclerView.setVisibility(View.GONE);
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        textViewSelected.setOnClickListener(v -> {
            if (recyclerView.getVisibility() == View.VISIBLE) {
                recyclerView.setVisibility(View.GONE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }
}
