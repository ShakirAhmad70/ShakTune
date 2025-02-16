package com.shakspotify.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.shakspotify.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private TextView splash_text;
    private SharedPreferences loginPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView splash_image = findViewById(R.id.splash_image);
        splash_text = findViewById(R.id.splash_text);

        loginPreference = getSharedPreferences(String.valueOf(R.string.LOGIN_PREF), MODE_PRIVATE);

        new Handler().postDelayed(() ->{
            boolean isLogin = loginPreference.getBoolean(String.valueOf(R.string.CHECK_LOGIN), false);
            Intent checkLoginIntent;
            if(isLogin)  checkLoginIntent = new Intent(SplashActivity.this, MainActivity.class);
            else         checkLoginIntent = new Intent(SplashActivity.this, AskLogin.class);

            startActivity(checkLoginIntent);
            finish();

        }, 2500);

        Animation fadeInAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation transAboveAnim = AnimationUtils.loadAnimation(this, R.anim.translate_above);
        Animation scaleDownAnim = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        Animation slideUpAnim = AnimationUtils.loadAnimation(this, R.anim.slide_in);

        AnimationSet animationSetForSplashImg = new AnimationSet(true);
        animationSetForSplashImg.addAnimation(fadeInAnim);
        animationSetForSplashImg.addAnimation(transAboveAnim);
        animationSetForSplashImg.addAnimation(scaleDownAnim);

        splash_image.startAnimation(animationSetForSplashImg);
        animationSetForSplashImg.setFillAfter(true);

        new Handler().postDelayed(() -> {
            splash_text.setVisibility(View.VISIBLE);
            splash_text.startAnimation(slideUpAnim);
        }, 1000);

    }
}