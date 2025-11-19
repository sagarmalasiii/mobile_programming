package com.sagar.mobileprogramming;// change this to your actual package name

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvRegister;
    CheckBox rememberMe;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        rememberMe = findViewById(R.id.rememberMe);

        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);


        boolean isRemembered = prefs.getBoolean("rememberMe", false);
        if (isRemembered) {
            etUsername.setText(prefs.getString("savedUsername", ""));
            etPassword.setText(prefs.getString("savedPassword", ""));
            rememberMe.setChecked(true);
        }


        btnLogin.setOnClickListener(v -> {
            String enteredUser = etUsername.getText().toString().trim();
            String enteredPass = etPassword.getText().toString().trim();
            Intent intent = new Intent(LoginActivity.this, UserlistActivity.class);
            startActivity(intent);
            finish();
            // Retrieve data stored from RegisterActivity
            String savedUser = prefs.getString("username", "");
            String savedPass = prefs.getString("password", "");

            if (enteredUser.equals(savedUser) && enteredPass.equals(savedPass)) {


                SharedPreferences.Editor editor = prefs.edit();
                if (rememberMe.isChecked()) {
                    editor.putBoolean("rememberMe", true);
                    editor.putString("savedUsername", enteredUser);
                    editor.putString("savedPassword", enteredPass);
                } else {
                    editor.putBoolean("rememberMe", false);
                    editor.remove("savedUsername");
                    editor.remove("savedPassword");
                }
                editor.apply();

                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(LoginActivity.this, UserlistActivity.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }
        });


        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
