package com.shakspotify.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.shakspotify.R;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        overridePendingTransition(R.anim.slide_in_right_for_activity, R.anim.slide_out_left_for_activity);

        LinearLayout withFacebook = findViewById(R.id.withFacebook);
        LinearLayout withGoogle = findViewById(R.id.withGoogle);
        LinearLayout withGmail = findViewById(R.id.withGmail);
        LinearLayout withPhoneNo = findViewById(R.id.withPhoneNo);
        TextView goBackToSignUp = findViewById(R.id.goBackToSignUp);


        withGmail.setOnClickListener((v) -> {
            Intent intent = new Intent(LoginActivity.this, EmailSignUpAndLogin.class);
            intent.putExtra("comeFrom", "login");
            startActivity(intent);
        });

        withPhoneNo.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, PhoneSignUpAndLogin.class);
            startActivity(intent);
        });


        goBackToSignUp.setOnClickListener((v) -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });


        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(LoginActivity.this, AskLogin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left_for_activity, R.anim.slide_in_right_for_activity);
                finish();
            }
        });    }
}