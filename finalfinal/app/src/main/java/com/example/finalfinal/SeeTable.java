package com.example.finalfinal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SeeTable extends AppCompatActivity {

    private TextView table1Status, table1Order;
    private TextView table2Status, table2Order;
    private TextView table3Status, table3Order;
    private TextView table4Status, table4Order;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_table);

        table1Status = findViewById(R.id.table1Status);
        table1Order = findViewById(R.id.table1Order);
        table2Status = findViewById(R.id.table2Status);
        table2Order = findViewById(R.id.table2Order);
        table3Status = findViewById(R.id.table3Status);
        table3Order = findViewById(R.id.table3Order);
        table4Status = findViewById(R.id.table4Status);
        table4Order = findViewById(R.id.table4Order);

        FirebaseDatabase.getInstance().getReference().child("masalar").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String masa = snapshot.getKey();
                        String durum = snapshot.child("durum").getValue(String.class);
                        String siparis = snapshot.child("siparis").getValue(String.class);

                        switch (masa) {
                            case "masa1":
                                table1Status.setText(durum);
                                table1Order.setText(siparis);
                                updateTableBackground(table1Status, durum);
                                break;
                            case "masa2":
                                table2Status.setText(durum);
                                table2Order.setText(siparis);
                                updateTableBackground(table2Status, durum);
                                break;
                            case "masa3":
                                table3Status.setText(durum);
                                table3Order.setText(siparis);
                                updateTableBackground(table3Status, durum);
                                break;
                            case "masa4":
                                table4Status.setText(durum);
                                table4Order.setText(siparis);
                                updateTableBackground(table4Status, durum);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void updateTableBackground(TextView statusTextView, String durum) {
        int colorResId = R.color.black;
        switch (durum) {
            case "boş":
                colorResId = R.color.black;
                break;
            case "rezerve":
                colorResId = R.color.black;
                break;
            case "dolu":
                colorResId = R.color.black;
                break;
        }
        statusTextView.setBackgroundResource(colorResId);
    }
}
