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

public class SignUpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        overridePendingTransition(R.anim.slide_in_right_for_activity, R.anim.slide_out_left_for_activity);

        LinearLayout withGoogle = findViewById(R.id.withGoogle);
        LinearLayout withPhoneNo = findViewById(R.id.withPhoneNo);
        LinearLayout withGmail = findViewById(R.id.withGmail);
        LinearLayout withFacebook = findViewById(R.id.withFacebook);
        TextView goBackToLogin = findViewById(R.id.goBackToLogin);

        withGmail.setOnClickListener((v) -> {
            Intent intent = new Intent(SignUpActivity.this, EmailSignUpAndLogin.class);
            intent.putExtra("comeFrom", "signUp");
            startActivity(intent);
        });

        withPhoneNo.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, PhoneSignUpAndLogin.class);
            startActivity(intent);
        });

        goBackToLogin.setOnClickListener((v) -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(SignUpActivity.this, AskLogin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left_for_activity, R.anim.slide_in_right_for_activity);
                finish();
            }
        });
    }
}