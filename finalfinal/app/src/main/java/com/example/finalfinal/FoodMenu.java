package com.example.finalfinal;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class FoodMenu extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FoodAdapter adapter;
    private List<Fooditems> foodDrinkList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        foodDrinkList = generateFoodDrinkList(); // Yemek ve içecek verilerini içeren bir liste oluşturun
        adapter = new FoodAdapter(foodDrinkList);
        recyclerView.setAdapter(adapter);
    }

    private List<Fooditems> generateFoodDrinkList() {
        // Yemek ve içecek verilerini burada oluşturun ve doldurun
        List<Fooditems> list = new ArrayList<>();
        // Örnek verileri ekleyelim:
        list.add(new Fooditems("Pizza", "50TL", R.drawable.pizza));
        list.add(new Fooditems("Hamburger", "45TL", R.drawable.hamburger));
        list.add(new Fooditems("Salata", "20TL", R.drawable.salata));
        list.add(new Fooditems("Çorba", "20TL", R.drawable.corba));
        list.add(new Fooditems("Kola", "8TL", R.drawable.cola));
        list.add(new Fooditems("Çay", "6TL", R.drawable.cay));
        list.add(new Fooditems("Ayran", "6TL", R.drawable.ayran));
        list.add(new Fooditems("Su", "4TL", R.drawable.su));

        // İsteğe bağlı olarak daha fazla yemek ve içecek ekleyebilirsiniz

        return list;
    }
}