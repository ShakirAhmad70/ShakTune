package com.shakspotify.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.shakspotify.R;

public class SelectGenderActivity extends AppCompatActivity {

    private RadioButton lastCheckedRB = null, femaleRB, maleRB, nonBinaryRB, othersRB, notSayRB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_gender);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        femaleRB = findViewById(R.id.femaleRB);
        maleRB = findViewById(R.id.maleRB);
        nonBinaryRB = findViewById(R.id.nonBinaryRB);
        othersRB = findViewById(R.id.othersRB);
        notSayRB = findViewById(R.id.notSayRB);

        performClickOnRadioBtn();


    }

    private void performClickOnRadioBtn() {
        View.OnClickListener radioBtnListener = v -> {
            RadioButton currentRB = (RadioButton) v;
            if (lastCheckedRB != null && lastCheckedRB != currentRB) {
                lastCheckedRB.setChecked(false);
                lastCheckedRB.setBackground(ContextCompat.getDrawable(this, R.drawable.radio_btn_stroke_transparent_bg));
            }

            currentRB.setChecked(true);
            currentRB.setBackground(ContextCompat.getDrawable(this, R.drawable.radio_btn_stroke_green_bg));
            lastCheckedRB = currentRB;

            startActivity(new Intent(this, SelectUserNameActivity.class));
        };

        femaleRB.setOnClickListener(radioBtnListener);
        maleRB.setOnClickListener(radioBtnListener);
        nonBinaryRB.setOnClickListener(radioBtnListener);
        othersRB.setOnClickListener(radioBtnListener);
        notSayRB.setOnClickListener(radioBtnListener);
    }
}