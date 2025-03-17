package com.shakspotify.activities.loginandsignup;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.graphics.Insets;
import androidx.core.text.HtmlCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.shakspotify.R;

public class SelectUserNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_user_name);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AppCompatButton createAccBtn = findViewById(R.id.createAccBtn);
        AppCompatTextView termsTxt = findViewById(R.id.termsTxt);
        AppCompatTextView privacyTxt = findViewById(R.id.privacyTxt);

        termsTxt.setText(HtmlCompat.fromHtml("<u>" + getString(R.string.terms_of_use) + "</u>", HtmlCompat.FROM_HTML_MODE_LEGACY));
        privacyTxt.setText(HtmlCompat.fromHtml("<u>" + getString(R.string.privacy_policy) + "</u>", HtmlCompat.FROM_HTML_MODE_LEGACY));

        createAccBtn.setOnClickListener((v) -> {
            Intent intent = new Intent(SelectUserNameActivity.this, SelectMusicLangActivity.class);
            startActivity(intent);
        });

    }
}