package com.example.finalfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClearTable extends AppCompatActivity {

    private DatabaseReference masalarRef;
    private TextView table1Status, table2Status, table3Status, table4Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_table);

        masalarRef = FirebaseDatabase.getInstance().getReference("masalar");

        table1Status = findViewById(R.id.table1clearstat);
        table2Status = findViewById(R.id.table2clearstat);
        table3Status = findViewById(R.id.table3clearstat);
        table4Status = findViewById(R.id.table4clearstat);

        ImageButton resetButton1 = findViewById(R.id.cleartable1);
        ImageButton resetButton2 = findViewById(R.id.cleartable2);
        ImageButton resetButton3 = findViewById(R.id.cleartable3);
        ImageButton resetButton4 = findViewById(R.id.cleartable4);

        resetButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndResetTable("masa1");
            }
        });

        resetButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndResetTable("masa2");
            }
        });

        resetButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndResetTable("masa3");
            }
        });

        resetButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndResetTable("masa4");
            }
        });
        setupMasaDurumListener(resetButton1, "masa1");
        setupMasaDurumListener(resetButton2, "masa2");
        setupMasaDurumListener(resetButton3, "masa3");
        setupMasaDurumListener(resetButton4, "masa4");
    }

    private void setupMasaDurumListener(final ImageButton masaButton, final String masaId) {
        masalarRef.child(masaId).child("durum").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String durum = dataSnapshot.getValue(String.class);
                updateMasaButtonColor(masaButton, durum);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Hata", "Masaların durumu alınamadı: " + databaseError.getMessage());
            }
        });
    }

    private void updateMasaButtonColor(ImageButton masaButton, String durum) {
        switch (masaButton.getId()) {
            case R.id.cleartable1:
                if (durum != null && (durum.equals("dolu") || durum.equals("rezerve"))) {
                    masaButton.setColorFilter(Color.RED);
                    table1Status.setText("Dolu");
                } else {
                    masaButton.setColorFilter(Color.GREEN);
                    table1Status.setText("Boş");
                }
                break;
            case R.id.cleartable2:
                if (durum != null && (durum.equals("dolu") || durum.equals("rezerve"))) {
                    masaButton.setColorFilter(Color.RED);
                    table2Status.setText("Dolu");
                } else {
                    masaButton.setColorFilter(Color.GREEN);
                    table2Status.setText("Boş");
                }
                break;
            case R.id.cleartable3:
                if (durum != null && (durum.equals("dolu") || durum.equals("rezerve"))) {
                    masaButton.setColorFilter(Color.RED);
                    table3Status.setText("Dolu");
                } else {
                    masaButton.setColorFilter(Color.GREEN);
                    table3Status.setText("Boş");
                }
                break;
            case R.id.cleartable4:
                if (durum != null && (durum.equals("dolu") || durum.equals("rezerve"))) {
                    masaButton.setColorFilter(Color.RED);
                    table4Status.setText("Dolu");
                } else {
                    masaButton.setColorFilter(Color.GREEN);
                    table4Status.setText("Boş");
                }
                break;
            default:
                break;
        }
    }

    private void resetMasaDurumuVeSiparisi(String masaId) {
        DatabaseReference masaRef = masalarRef.child(masaId);
        masaRef.child("durum").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String durum = dataSnapshot.getValue(String.class);
                if (durum != null && durum.equals("boş")) {
                    Toast.makeText(ClearTable.this, "Boş masa zaten boşaltılamaz.", Toast.LENGTH_SHORT).show();
                } else {
                    masaRef.child("durum").setValue("boş");
                    masaRef.child("siparis").setValue("");
                    Toast.makeText(ClearTable.this, "Masa boşaltıldı.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Hata", "Masanın durumu alınamadı: " + databaseError.getMessage());
            }
        });
    }

    private void checkAndResetTable(final String masaId) {
        DatabaseReference masaRef = masalarRef.child(masaId);
        masaRef.child("durum").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String durum = dataSnapshot.getValue(String.class);
                if (durum != null && durum.equals("boş")) {
                    Toast.makeText(ClearTable.this, "Boş masa zaten boşaltılamaz.", Toast.LENGTH_SHORT).show();
                } else {
                    resetMasaDurumuVeSiparisi(masaId);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Hata", "Masanın durumu alınamadı: " + databaseError.getMessage());
            }
        });
    }
}
