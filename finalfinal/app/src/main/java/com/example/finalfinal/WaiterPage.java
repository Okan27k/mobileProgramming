package com.example.finalfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WaiterPage extends AppCompatActivity {
    private Button takeOrder, clearTable, seeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_page);

        takeOrder = findViewById(R.id.takeorderbutton);
        clearTable = findViewById(R.id.cleartablebutton);
        seeTable = findViewById(R.id.seetablebutton);

        takeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WaiterPage.this, TakeOrder.class));
            }
        });

        clearTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WaiterPage.this, ClearTable.class));
            }
        });

        seeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WaiterPage.this, SeeTable.class));
            }
        });
    }
}
