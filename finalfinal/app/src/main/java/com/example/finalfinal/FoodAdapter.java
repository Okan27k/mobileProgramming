package com.example.finalfinal;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private List<Fooditems> foodDrinkList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewFoodDrink;
        public TextView textViewFoodDrinkName;
        public TextView textViewFoodDrinkPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewFoodDrink = itemView.findViewById(R.id.imageViewFoodDrink);
            textViewFoodDrinkName = itemView.findViewById(R.id.textViewFoodDrinkName);
            textViewFoodDrinkPrice = itemView.findViewById(R.id.textViewFoodDrinkPrice);
        }
    }

    public FoodAdapter(List<Fooditems> foodDrinkList) {
        this.foodDrinkList = foodDrinkList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_fooditems, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fooditems Fooditems = foodDrinkList.get(position);
        holder.imageViewFoodDrink.setImageResource(Fooditems.getImageResource());
        holder.textViewFoodDrinkName.setText(Fooditems.getName());
        holder.textViewFoodDrinkPrice.setText(Fooditems.getPrice());
    }

    @Override
    public int getItemCount() {
        return foodDrinkList.size();
    }
}

