package com.nrzm.retrofitlocaldemo;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<FoodNutrition.FoodNutrtionInfo.FoodDetail> itemList;
    private int selectedPosition = RecyclerView.NO_POSITION;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    public ListAdapter(List<FoodNutrition.FoodNutrtionInfo.FoodDetail> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodNutrition.FoodNutrtionInfo.FoodDetail foodDetail = itemList.get(position);
        holder.tvName.setText(foodDetail.getDescKor());
        holder.tvMakerName.setText(foodDetail.getMakerName());
        holder.tvResearchYear.setText(foodDetail.getResearchYear());

        holder.tvName.setOnClickListener(v -> {
            boolean isChecked = holder.tvName.isChecked();
            holder.tvName.setChecked(!isChecked);

            if (holder.tvName.isChecked()) {
                selectedItems.put(position, true);
            } else {
                selectedItems.delete(position);
            }

            List<FoodNutrition.FoodNutrtionInfo.FoodDetail> selectedItems = getSelectedItems();

            StringBuffer sb = new StringBuffer();
            for (FoodNutrition.FoodNutrtionInfo.FoodDetail food : selectedItems) {
                sb.append(food.getDescKor()).append(", ");
            }

            Snackbar.make(v, sb.toString(), Snackbar.LENGTH_LONG)
                    .setAction("Action", v1 -> {
                        // 액션 버튼 클릭 시 수행할 동작
                    })
                    .show();
        });

        holder.itemView.setSelected(selectedPosition == position);
        holder.itemView.setOnClickListener(v -> {
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(selectedPosition);

            String msg = foodDetail.getDescKor() + "는 " + foodDetail.getCalories() + "칼로리 입니다";

            Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
                    .setAction("Action", v1 -> {
                        // 액션 버튼 클릭 시 수행할 동작
                    })
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public List<FoodNutrition.FoodNutrtionInfo.FoodDetail> getSelectedItems() {
        List<FoodNutrition.FoodNutrtionInfo.FoodDetail> selectedItemsList = new ArrayList<>();
        for (int i = 0; i < selectedItems.size(); i++) {
            int position = selectedItems.keyAt(i);
            if (selectedItems.get(position, false)) {
                selectedItemsList.add(itemList.get(position));
            }
        }
        return selectedItemsList;
    }

    public void updateList(List<FoodNutrition.FoodNutrtionInfo.FoodDetail> foodDetails) {
        this.itemList = foodDetails;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatCheckedTextView tvName;
        TextView tvMakerName, tvResearchYear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMakerName = itemView.findViewById(R.id.tvMakerName);
            tvResearchYear = itemView.findViewById(R.id.tvResearchYear);
        }
    }
}