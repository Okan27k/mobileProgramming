package com.example.project3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity{

    String compName[] ={"HP","ASUS","Apple","Lenovo","Monster"};
    String compDesc[] ={"HP Victus  Core I7-13700H  16GB Ram 1TB SSD RTX 4050 Ekran Kartı 15.6 WIN11  Mika Gümüş"
            , "ASUS Core i7-13650HX DDR5  16GB Ram 512GB SSD TX4050 Ekran Kartı 16 Gri"
            ,"APPLE Mac Air M1 16GB RAM  256GB SSD 13.3 inç Uzay Grisi"
            , "LENOVO 4GB Ram 128GB emmc  15.6 Win 11 Gri"
            ,"Monster Core I7-13700H 16GB Ram 512GB SSD RTX 3050 Ti 144Hz 15.6 inç Siyah"};
    String compPrice[] ={"29999TL","41349TL","23999TL","16799TL","34999"};
    int images[] ={R.drawable.hp,R.drawable.asus,R.drawable.apple,R.drawable.dort,R.drawable.bes};


    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(this,compName,compDesc,compPrice,images);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }





}

