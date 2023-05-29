package com.example.finalfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TakeOrder extends AppCompatActivity {

    private EditText editTextTableNumber;
    private EditText editTextOrder;
    private Button buttonPlaceOrder;
    private DatabaseReference masalarRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_order);

        masalarRef = FirebaseDatabase.getInstance().getReference("masalar");

        editTextTableNumber = findViewById(R.id.editTextTableNumber);
        editTextOrder = findViewById(R.id.editTextOrder);
        buttonPlaceOrder = findViewById(R.id.buttonPlaceOrder);

        buttonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeOrder();
            }
        });
    }

    private void placeOrder() {
        String tableNumber = editTextTableNumber.getText().toString().trim();
        String orderText = editTextOrder.getText().toString().trim();

        if (tableNumber.isEmpty() || orderText.isEmpty()) {
            Toast.makeText(this, "Masa numarası ve sipariş boş olamaz", Toast.LENGTH_SHORT).show();
            return;
        }


        DatabaseReference masaRef = masalarRef.child("masa" + tableNumber);
        masaRef.child("durum").setValue("dolu");
        masaRef.child("siparis").setValue(orderText);


        Toast.makeText(this, "Siparişiniz alındı", Toast.LENGTH_SHORT).show();


        editTextTableNumber.setText("");
        editTextOrder.setText("");
    }
}