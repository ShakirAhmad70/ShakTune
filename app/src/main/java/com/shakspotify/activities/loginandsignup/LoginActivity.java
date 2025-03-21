package com.shakspotify.activities.loginandsignup;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.shakspotify.R;
import com.shakspotify.activities.MainActivity;

import java.util.List;

public class LoginActivity extends AppCompatActivity {


    private static final int GOOGLE_LOGIN_REQ_CODE = 1000;
    private CallbackManager fbCallbackManager;

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

        LinearLayout loginWithFacebook = findViewById(R.id.loginWithFacebook);
        LinearLayout loginWithGoogle = findViewById(R.id.loginWithGoogle);
        LinearLayout loginWithGmail = findViewById(R.id.loginWithGmail);
        LinearLayout loginWithPhoneNo = findViewById(R.id.loginWithPhoneNo);
        TextView goBackToSignUp = findViewById(R.id.goBackToSignUp);


        loginWithGmail.setOnClickListener((v) -> {
            Intent intent = new Intent(LoginActivity.this, EmailSignUpAndLogin.class);
            intent.putExtra("comeFrom", "login");
            startActivity(intent);
        });

        loginWithPhoneNo.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, PhoneSignUpAndLogin.class);
            startActivity(intent);
        });


        //**********Google Sign In**********
        GoogleSignInOptions gsop = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleSignInClient gsc = GoogleSignIn.getClient(this, gsop);

        loginWithGoogle.setOnClickListener(v -> {
            Intent signInIntent = gsc.getSignInIntent();
            startActivityForResult(signInIntent, GOOGLE_LOGIN_REQ_CODE);
        });


        //**********Facebook Sign In**********
        fbCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(fbCallbackManager,
                new FacebookCallback<>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        //Handle cancel
                        Toast.makeText(LoginActivity.this, "Login canceled", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull FacebookException e) {
                        //Handle error
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                });

        loginWithFacebook.setOnClickListener(v -> {
            LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, List.of("public_profile"));
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
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Google sign in handling
        if (requestCode == GOOGLE_LOGIN_REQ_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Google sign in failed, account not found", Toast.LENGTH_SHORT).show();
                }
            } catch (ApiException e) {
                Log.e("LoginActivity", "Google sign in failed " + e.getStatus(), e);
            }
        }


        //Facebook sign in handling
        fbCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}