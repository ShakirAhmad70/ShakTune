package com.shakspotify.activities.loginandsignup;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shakspotify.R;
import com.shakspotify.adapters.ArtistsAdapter;
import com.shakspotify.adapters.SelectedLangAdapter;
import com.shakspotify.models.ArtistsItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelectArtistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_artists);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //Set up selected languages recycler view
        RecyclerView selectedLanguagesRv = findViewById(R.id.selectedLanguagesRv);
        selectedLanguagesRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        SelectedLangAdapter selectedLanguagesAdapter = getSelectedLangAdapter();
        selectedLanguagesRv.setAdapter(selectedLanguagesAdapter);


        //Set up artists recycler view
        RecyclerView artistsRv = findViewById(R.id.artistsRv);
        artistsRv.setLayoutManager(new GridLayoutManager(this, 3));
        ArtistsAdapter artistsAdapter = getArtistsAdapter();
        artistsRv.setAdapter(artistsAdapter);

    }

    private ArtistsAdapter getArtistsAdapter() {
        List<ArtistsItemModel> artistsList = new ArrayList<>();
        artistsList.add(new ArtistsItemModel("Adele", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("Ariana Grande", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("Beyonce", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("Ed Sheeran", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("Elton John", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("Justin Bieber", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("Katy Perry", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("Lana Del Rey", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("Lady Gaga", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("Miley Cyrus", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("One Direction", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("Taylor Swift", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("The ChainSmokers", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("The Weeknd", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("Twenty One Pilots", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("Zayn Malik", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("Zedd", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("Khalid", R.drawable.artist));
        artistsList.add(new ArtistsItemModel("Arijit Singh", R.drawable.artist));


        return new ArtistsAdapter(artistsList, getApplicationContext());
    }

    @NonNull
    private SelectedLangAdapter getSelectedLangAdapter() {
        Intent intent = getIntent();
        List<String> selectedLanguagesList = intent.getStringArrayListExtra("selectedLanguages");

        //I have added this condition because there was a problem whenever switching from dark to light or light to dark theme idk why it is adding multiple For You string every time
        if (!Objects.requireNonNull(selectedLanguagesList).get(0).equals("For You"))
            selectedLanguagesList.add(0, "For You");

        return new SelectedLangAdapter(selectedLanguagesList, getApplicationContext());
    }
}