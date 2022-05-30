package com.example.appuniform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText nick, email, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        nick = findViewById(R.id.et_nick);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);

        Button register = findViewById(R.id.btn_register);
        Button switchToLogin = findViewById(R.id.btn_switch);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nick_s = nick.getText().toString().trim();
                String email_s = email.getText().toString().trim();
                String password_s = password.getText().toString().trim();

                if (nick_s.isEmpty() || email_s.isEmpty() || password_s.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Podaj wszystkie dane!", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email_s, password_s).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Rejestracja udana", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(RegisterActivity.this, "Rejestracja nieudana", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        switchToLogin.setOnClickListener(v-> {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));

        });

    }
}