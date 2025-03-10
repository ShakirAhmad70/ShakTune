package com.shakspotify.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.shakspotify.R;
import com.shakspotify.utils.Validator;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class EmailSignUpAndLogin extends AppCompatActivity {

    AppCompatEditText askEmailEdt;
    AppCompatButton nextBtn;
    TextInputLayout askPasswordTxtInpLay;
    TextInputEditText askPasswordTxtInpEdt;


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
        nextBtn = findViewById(R.id.nextBtn);
        askEmailEdt = findViewById(R.id.askEmailEdt);
        AppCompatButton loginWithoutPasswordBtn = findViewById(R.id.loginWithoutPasswordBtn);
        askPasswordTxtInpLay = findViewById(R.id.askPasswordTxtInpLay);
        askPasswordTxtInpEdt = findViewById(R.id.askPasswordTxtInpEdt);


        nextBtn.setEnabled(false);

        Intent intent = getIntent();
        String comeFrom = intent.getStringExtra("comeFrom");
        AtomicReference<TextWatcher> inputWatcher;

        if(Objects.requireNonNull(comeFrom).equals("signUp")) {
            askEmailTxt.setText(R.string.what_s_your_email);
            askPasswordTxt.setVisibility(View.GONE);
            askPasswordTxtInpLay.setVisibility(View.GONE);
            loginWithoutPasswordBtn.setVisibility(View.GONE);

            inputWatcher = new AtomicReference<>(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    checkInputValidityFor("email");
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
            askEmailEdt.addTextChangedListener(inputWatcher.get());


            //Make Password input visible after validating email
            nextBtn.setOnClickListener(v -> {
                String email = Objects.requireNonNull(askEmailEdt.getText()).toString().trim();
                if (Validator.isValidEmail(email)) {
                    askEmailTxt.setVisibility(View.GONE);
                    askEmailEdt.setVisibility(View.GONE);
                    confirmTxt.setVisibility(View.GONE);
                    askPasswordTxtInpLay.setVisibility(View.VISIBLE);
                    askPasswordTxt.setVisibility(View.VISIBLE);
                    askPasswordTxt.setText(R.string.createYourPassword);

                    inputWatcher.set(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            checkInputValidityFor("password");
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                        }
                    });
                    askPasswordTxtInpEdt.addTextChangedListener(inputWatcher.get());

                    nextBtn.setOnClickListener(v1 -> {
                        String password = Objects.requireNonNull(askPasswordTxtInpEdt.getText()).toString().trim();

                        if (Validator.isValidPassword(password)) {
                            askPasswordTxt.setText(R.string.confirmYourPassword);
                            askPasswordTxtInpEdt.setText("");

                            nextBtn.setOnClickListener(v2 -> {
                                String confirmPassword = askPasswordTxtInpEdt.getText().toString().trim();

                                if (password.equals(confirmPassword)) {
                                    Intent gotoSolveCaptchaActivity = new Intent(EmailSignUpAndLogin.this, SelectDobActivity.class);
                                    startActivity(gotoSolveCaptchaActivity);
                                } else {
                                    Toast.makeText(this, "Entered Passwords don't match, please try again!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(this, "Password does not meet requirements!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });


        } else if(comeFrom.equals("login")) {
            askEmailTxt.setText(R.string.email_or_username);
            askPasswordTxt.setText(R.string.password);
            nextBtn.setText(R.string.log_in);
            confirmTxt.setVisibility(View.GONE);

            inputWatcher = new AtomicReference<>(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    checkInputValidityFor("both");
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            askEmailEdt.addTextChangedListener(inputWatcher.get());

        } else {
            Log.d("EmailSignUpAndLogin", "onCreate: " + comeFrom);
        }

    }

    private void checkInputValidityFor(String tag) {
        boolean valid = false;  // false by default
        //set the color to warning color until validation is done
        askEmailEdt.setTextColor(ContextCompat.getColor(this, R.color.warningRed));
        askPasswordTxtInpEdt.setTextColor(ContextCompat.getColor(this, R.color.warningRed));

        if (tag.equalsIgnoreCase("email") && askEmailEdt.getText() != null) {
            String email = askEmailEdt.getText().toString().trim();
            valid = Validator.isValidEmail(email);
        } else if (tag.equalsIgnoreCase("password") && askPasswordTxtInpEdt.getText() != null) {
            String password = askPasswordTxtInpEdt.getText().toString().trim();
            valid = Validator.isValidPassword(password);
        } else if (tag.equalsIgnoreCase("both") && askEmailEdt.getText() != null && askPasswordTxtInpEdt.getText() != null) {
            String email = askEmailEdt.getText().toString().trim();
            String password = askPasswordTxtInpEdt.getText().toString().trim();
            valid = Validator.isValidEmail(email) && Validator.isValidPassword(password);
        }
        nextBtn.setEnabled(valid);
        if (valid) {
            askEmailEdt.setTextColor(ContextCompat.getColor(this, R.color.white));
            askPasswordTxtInpEdt.setTextColor(ContextCompat.getColor(this, R.color.white));
        }
        // Adjust alpha: 1.0 for enabled, 0.5 for disabled
        nextBtn.setAlpha(valid ? 1.0f : 0.5f);
    }
}