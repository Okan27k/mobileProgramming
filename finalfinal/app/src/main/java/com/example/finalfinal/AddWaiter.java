package com.example.finalfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddWaiter extends AppCompatActivity {
    private EditText editEmail, editPassword, editisim;
    private String txtemail, txtpassword, txtisim;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mReference;
    private HashMap<String, Object> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_waiter);
        editEmail = findViewById(R.id.staffEmail);
        editPassword = findViewById(R.id.staffPassword);
        editisim = findViewById(R.id.staffisim);
        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();
    }

    public void signWaiter(View v) {
        txtemail = editEmail.getText().toString();
        txtpassword = editPassword.getText().toString();
        txtisim = editisim.getText().toString();
        if (!TextUtils.isEmpty(txtisim) && !TextUtils.isEmpty(txtemail) && !TextUtils.isEmpty(txtpassword)) {
            mAuth.createUserWithEmailAndPassword(txtemail, txtpassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mUser = mAuth.getCurrentUser();
                                String employeeID = mReference.push().getKey(); // Generate unique ID for the employee

                                mData = new HashMap<>();
                                mData.put("kullaniciAdi", txtisim);
                                mData.put("kullaniciEmail", txtemail);
                                mData.put("kullaniciSifre", txtpassword);
                                mData.put("kullaniciid", employeeID); // Save the generated ID

                                mReference.child("Waiters").child(employeeID).setValue(mData)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(AddWaiter.this, "Kullanıcı kaydedildi", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(AddWaiter.this, "Kullanıcı kaydedilemedi", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(AddWaiter.this, "Kayıt Başarısız", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        } else {
            Toast.makeText(this, "Email ve şifre boş olamaz", Toast.LENGTH_LONG).show();
        }

    }
}
