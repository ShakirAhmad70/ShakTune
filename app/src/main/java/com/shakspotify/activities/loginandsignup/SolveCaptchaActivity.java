package com.shakspotify.activities.loginandsignup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.safetynet.SafetyNet;
import com.shakspotify.R;

import java.util.Objects;

public class SolveCaptchaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_solve_captcha);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AppCompatCheckBox checkBox = findViewById(R.id.recaptchaCheckbox);
        AppCompatButton continueBtn = findViewById(R.id.continueBtn);

        //TODO:- set this btn`s enabled to false when recaptcha problem is fixed
        continueBtn.setEnabled(true);

        checkBox.setOnClickListener(v -> {
            if (checkBox.isChecked()) {
                SafetyNet.getClient(this).verifyWithRecaptcha(getString(R.string.RECAPTCHA_SITE_KEY))
                        .addOnSuccessListener(this, response -> {
                            String token = Objects.requireNonNull(response.getTokenResult());
                            if (!token.isEmpty()) {
                                continueBtn.setEnabled(true);
                                continueBtn.setAlpha(1.0f);
                            }
                        })
                        .addOnFailureListener(this, e -> {
                            Toast.makeText(SolveCaptchaActivity.this, "Captcha verification failed!", Toast.LENGTH_LONG).show();
                            checkBox.setChecked(false);
                        });
            } else {
                Toast.makeText(SolveCaptchaActivity.this, "Captcha not verified, please try again", Toast.LENGTH_LONG).show();
            }
        });

        continueBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SolveCaptchaActivity.this, SelectMusicLangActivity.class);
            startActivity(intent);
        });
    }
}
