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

public class WaiterLogin extends AppCompatActivity {
    private EditText editEmail,editPassword;
    private String txtemail,txtpassword;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Button managerLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_login);
        managerLogin =(Button) findViewById(R.id.managerLogPage);
        editEmail =(EditText) findViewById(R.id.staffLoginEmail);
        editPassword =(EditText) findViewById(R.id.staffLoginPass);
        mAuth = FirebaseAuth.getInstance();
        managerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WaiterLogin.this,ManagerLogin.class));
            }
        });
    }

    public void logIN(View v){
        txtemail = editEmail.getText().toString();
        txtpassword = editPassword.getText().toString();
        if(!TextUtils.isEmpty(txtemail) &&  !TextUtils.isEmpty(txtpassword)){
            mAuth.signInWithEmailAndPassword(txtemail,txtpassword)
                    .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            startActivity(new Intent(WaiterLogin.this,WaiterPage.class));

                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(WaiterLogin.this, "Giriş Başarısız", Toast.LENGTH_SHORT).show();

                        }
                    });
        }else
            Toast.makeText(this,"Email ve şifre boş olamaz",Toast.LENGTH_LONG).show();


    }



}