package com.example.finalfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Fooditems extends AppCompatActivity {

    private String name;
    private String price;
    private int imageResource;

    public Fooditems(String name, String price, int imageResource) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }
}