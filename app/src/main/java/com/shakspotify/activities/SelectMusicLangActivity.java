package com.shakspotify.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shakspotify.R;
import com.shakspotify.adapters.MusicLangAdapter;
import com.shakspotify.models.MusicLangItemModel;

import java.util.ArrayList;
import java.util.List;

public class SelectMusicLangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_music_lang);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView musicLangRv = findViewById(R.id.musicLangRv);
        AppCompatButton nextBtn = findViewById(R.id.nextBtn);

        musicLangRv.setLayoutManager(new GridLayoutManager(this, 2));

        List<MusicLangItemModel> musicLangItemsList = new ArrayList<>();

        musicLangItemsList.add(new MusicLangItemModel("Hindi", R.drawable.hindi_artist, Color.parseColor("#FF5722"), Color.parseColor("#E91E63")));
        musicLangItemsList.add(new MusicLangItemModel("English", R.drawable.international_artist, Color.parseColor("#FF9800"), Color.parseColor("#FFC107")));
        musicLangItemsList.add(new MusicLangItemModel("Punjabi", R.drawable.punjabi_artist, Color.parseColor("#9C27B0"), Color.parseColor("#673AB7")));
        musicLangItemsList.add(new MusicLangItemModel("Tamil", R.drawable.tamil_artist, Color.parseColor("#FFEB3B"), Color.parseColor("#FBC02D")));
        musicLangItemsList.add(new MusicLangItemModel("Telugu", R.drawable.telugu_artist, Color.parseColor("#4CAF50"), Color.parseColor("#009688")));
        musicLangItemsList.add(new MusicLangItemModel("Malayalam", R.drawable.malayalam_artist, Color.parseColor("#8BC34A"), Color.parseColor("#CDDC39")));
        musicLangItemsList.add(new MusicLangItemModel("Marathi", R.drawable.marathi_artist, Color.parseColor("#FF9800"), Color.parseColor("#FF5722")));
        musicLangItemsList.add(new MusicLangItemModel("Gujarati", R.drawable.gujarati_artist, Color.parseColor("#E91E63"), Color.parseColor("#F44336")));
        musicLangItemsList.add(new MusicLangItemModel("Bengali", R.drawable.bengali_artist, Color.parseColor("#2196F3"), Color.parseColor("#3F51B5")));
        musicLangItemsList.add(new MusicLangItemModel("Kannada", R.drawable.kannada_artist, Color.parseColor("#D32F2F"), Color.parseColor("#FF1744")));


        //setup the adapter
        musicLangRv.setAdapter(new MusicLangAdapter(musicLangItemsList, this));

        nextBtn.setOnClickListener(v -> {
            startActivity(new Intent(SelectMusicLangActivity.this, SelectArtistsActivity.class));
            finish();
        });

    }
}