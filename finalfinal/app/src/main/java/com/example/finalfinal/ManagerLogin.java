package com.example.finalfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ManagerLogin extends AppCompatActivity {
    private EditText editEmail,editPassword;
    private String txtemail,txtpassword;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);
        editEmail =(EditText) findViewById(R.id.manageremail);
        editPassword =(EditText) findViewById(R.id.managerpass);
        mAuth = FirebaseAuth.getInstance();
    }

    public void managerlogIn(View v) {
        txtemail = editEmail.getText().toString();
        txtpassword = editPassword.getText().toString();

        if (TextUtils.isEmpty(txtemail) || TextUtils.isEmpty(txtpassword)) {
            Toast.makeText(this, "Email ve şifre boş olamaz", Toast.LENGTH_LONG).show();
            return;
        }

        if (!txtemail.equals("manager@gmail.com")) {
            Toast.makeText(this, "Sadece yöneticiler giriş yapabilir", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(txtemail, txtpassword)
                .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(ManagerLogin.this, ManagerPage.class));
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ManagerLogin.this, "Giriş Başarısız", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}