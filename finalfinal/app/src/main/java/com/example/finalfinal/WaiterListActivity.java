package com.example.finalfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WaiterListActivity extends AppCompatActivity {

    private ListView listViewUsers;
    private ArrayAdapter<String> adapter;
    private List<String> userList;
    private DatabaseReference usersRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_list);

        listViewUsers = findViewById(R.id.listViewUsers);
        userList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        listViewUsers.setAdapter(adapter);

        usersRef = FirebaseDatabase.getInstance().getReference().child("Waiters");
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String userName = snapshot.child("kullaniciAdi").getValue(String.class);
                    String userEmail = snapshot.child("kullaniciEmail").getValue(String.class);
                    String userInfo = "İsim: " + userName + "\nEmail Adresi: " + userEmail;
                    userList.add(userInfo);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(WaiterListActivity.this, "Veriler alınamadı: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
