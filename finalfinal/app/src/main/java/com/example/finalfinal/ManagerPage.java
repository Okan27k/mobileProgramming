package com.example.finalfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerPage extends AppCompatActivity {
    private Button addwaiter,waiterlist;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_page);
        addwaiter =(Button) findViewById(R.id.addwaiter);
        waiterlist = (Button) findViewById(R.id.waiterlist);
        addwaiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerPage.this,AddWaiter.class));
            }
        });
        waiterlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerPage.this, WaiterListActivity.class));
            }
        });
    }
}