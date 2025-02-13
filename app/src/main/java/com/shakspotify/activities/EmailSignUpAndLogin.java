package com.shakspotify.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.shakspotify.R;

import java.util.Objects;

public class EmailSignUpAndLogin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_email_sign_up_and_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AppCompatTextView askEmailTxt = findViewById(R.id.askEmailTxt);
        AppCompatTextView confirmTxt = findViewById(R.id.confirmTxt);
        AppCompatTextView askPasswordTxt = findViewById(R.id.askPasswordTxt);
        AppCompatButton nextBtn = findViewById(R.id.nextBtn);
        AppCompatButton loginWithoutPasswordBtn = findViewById(R.id.loginWithoutPasswordBtn);
        TextInputLayout askPasswordTxtInpLay = findViewById(R.id.askPasswordTxtInpLay);


        Intent intent = getIntent();
        String comeFrom = intent.getStringExtra("comeFrom");
        if(Objects.requireNonNull(comeFrom).equals("signUp")) {
            askEmailTxt.setText(R.string.what_s_your_email);
            askPasswordTxt.setVisibility(View.GONE);
            askPasswordTxtInpLay.setVisibility(View.GONE);
            loginWithoutPasswordBtn.setVisibility(View.GONE);
        } else if(comeFrom.equals("login")) {
            askEmailTxt.setText(R.string.email_or_username);
            askPasswordTxt.setText(R.string.password);
            nextBtn.setText(R.string.log_in);
            confirmTxt.setVisibility(View.GONE);
        } else {
            Log.d("EmailSignUpAndLogin", "onCreate: " + comeFrom);
        }
    }
}