package com.shakspotify.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.shakspotify.R;

import org.json.JSONException;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tv = findViewById(R.id.tv);
        SharedPreferences loginPref = getSharedPreferences(String.valueOf(R.string.LOGIN_PREF), MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPref.edit();
        editor.putBoolean(String.valueOf(R.string.CHECK_LOGIN), true);
        editor.apply();

        //clear tv before login
        tv.setText("");

        //**********Google Sign In**********
        GoogleSignInClient gsc = GoogleSignIn.getClient(this,
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build()
        );

        GoogleSignInAccount googleAcc = GoogleSignIn.getLastSignedInAccount(this);
        if (googleAcc != null) {
            //TODO: get user details from google account and store into database
            String personName = googleAcc.getDisplayName();
            String email = googleAcc.getEmail();
            tv.setText("Google Name: " + personName + ",\nEmail: " + email);
            tv.setTextColor(getResources().getColor(R.color.white, getTheme()));
            tv.setTextSize(18.3f);
        }

        //**********Facebook Sign In**********
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, (object, response) -> {
            //TODO: get user details from facebook and store into database
            if (object != null && response != null) {
                try {
                    //Check out the responses coming from the Graph API
                    Log.d("Object", object.toString());
                    Log.d("Response", object.toString());

                    String personName = object.getString("name");
                    tv.setText("Facebook Name: " + personName);
                    tv.setTextColor(getResources().getColor(R.color.white, getTheme()));
                    tv.setTextSize(18.3f);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,link,picture.type(large)");  //fetching specific fields
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();


        //**********Temporary Sign Out on TextView**********
        tv.setOnClickListener(v -> gsc.signOut().addOnCompleteListener(task -> {
            tv.setText("Signed out successfully");
            Toast.makeText(this, "Sign out successfully", Toast.LENGTH_SHORT).show();
            editor.putBoolean(String.valueOf(R.string.CHECK_LOGIN), false);
            editor.apply();
            startActivity(new Intent(MainActivity.this, AskLogin.class));
            finish();
        }));
    }
}