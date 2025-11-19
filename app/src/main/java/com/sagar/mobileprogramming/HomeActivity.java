package com.sagar.mobileprogramming; // change this to your actual package name

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView tvWelcome;
    Button btnLogout;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);
        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // ✅ Fetch and show user's full name or username
        String fullname = prefs.getString("fullname", null);
        String username = prefs.getString("username", "User");
        if (fullname != null && !fullname.isEmpty()) {
            tvWelcome.setText("Welcome, " + fullname + "!");
        } else {
            tvWelcome.setText("Welcome, " + username + "!");
        }

        // ✅ Handle logout
        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear(); // remove all saved user data
            editor.apply();

            // Go back to Login screen
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
