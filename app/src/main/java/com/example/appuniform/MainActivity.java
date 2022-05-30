package com.example.appuniform;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText email, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //inicjalizacja elementow z pliku xml
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        Button sign = findViewById(R.id.btn_sign);
        Button switchtoregister = findViewById(R.id.btn_switchToRegister);
        //instancja do autoryzacji za pomocą FIrebase
        mAuth = FirebaseAuth.getInstance();

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email3 = email.getText().toString().trim();
                String haslo3 = password.getText().toString().trim();

                if (email3.isEmpty() || haslo3.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Podaj wszystkie dane!", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email3, haslo3).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Logowanie udane", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, ListActivity.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Logowanie nieudane", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        switchtoregister.setOnClickListener(v-> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));

        });


    }
}