package com.shakspotify.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.shakspotify.R;

public class AskLogin extends AppCompatActivity {

    SharedPreferences loginPreference;
    AppCompatButton loginBtn, signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ask_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);

        loginPreference = getSharedPreferences("login", MODE_PRIVATE);

        if (loginPreference.getBoolean("isLogin", false)) {
            Intent intent = new Intent(AskLogin.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        signUpBtn.setOnClickListener((v) -> {
            Intent intent = new Intent(AskLogin.this, SignUpActivity.class);
            startActivity(intent);
        });

        loginBtn.setOnClickListener((v) -> {
            Intent intent = new Intent(AskLogin.this, LoginActivity.class);
            startActivity(intent);
        });

    }
}