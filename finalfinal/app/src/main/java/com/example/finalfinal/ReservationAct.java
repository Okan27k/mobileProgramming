package com.example.finalfinal;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.*;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ReservationAct extends AppCompatActivity {

    private DatabaseReference masalarRef;

    private ImageButton masa1;
    private TextView masa1Durum;

    private ImageButton masa2;
    private TextView masa2Durum;

    private ImageButton masa3;
    private TextView masa3Durum;

    private ImageButton masa4;
    private TextView masa4Durum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        masalarRef = FirebaseDatabase.getInstance().getReference("masalar");

        masa1 = findViewById(R.id.table1id);
        masa1Durum = findViewById(R.id.table1Status);

        masa2 = findViewById(R.id.table2id);
        masa2Durum = findViewById(R.id.table2Status);

        masa3 = findViewById(R.id.table3id);
        masa3Durum = findViewById(R.id.table3Status);

        masa4 = findViewById(R.id.table4id);
        masa4Durum = findViewById(R.id.table4Status);

        setupMasaDurumListener(masa1, "masa1", masa1Durum);
        setupMasaDurumListener(masa2, "masa2", masa2Durum);
        setupMasaDurumListener(masa3, "masa3", masa3Durum);
        setupMasaDurumListener(masa4, "masa4", masa4Durum);

        masa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndReserveTable("masa1");
            }
        });

        masa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndReserveTable("masa2");
            }
        });

        masa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndReserveTable("masa3");
            }
        });

        masa4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndReserveTable("masa4");
            }
        });
    }

    private void setupMasaDurumListener(final ImageButton masaButton, final String masaId, final TextView masaDurum) {
        masalarRef.child(masaId).child("durum").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String durum = dataSnapshot.getValue(String.class);
                updateMasaButtonColor(masaButton, durum);
                masaDurum.setText(durum);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Hata", "Masaların durumları alınamadı: " + databaseError.getMessage());
            }
        });
    }

    private void updateMasaButtonColor(ImageButton masaButton, String durum) {
        if (durum != null && durum.equals("dolu") || durum.equals("rezerve")) {
            masaButton.setColorFilter(Color.RED);
        } else {
            masaButton.setColorFilter(Color.GREEN);
        }
    }

    private void checkAndReserveTable(final String masaId) {
        masalarRef.child(masaId).child("durum").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String durum = dataSnapshot.getValue(String.class);
                if (durum != null && durum.equals("boş")) {
                    reserveTable(masaId);
                    Toast.makeText(ReservationAct.this, "Rezerve edilmiştir", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ReservationAct.this, "Masa rezervasyonu yapılamaz", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Hata", "Masaların durumları alınamadı: " + databaseError.getMessage());
            }
        });
    }

    private void reserveTable(String masaId) {
        masalarRef.child(masaId).child("durum").setValue("rezerve");
    }
}
