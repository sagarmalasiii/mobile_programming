package com.sagar.mobileprogramming;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText fullname, username, password, email, address, phone;
    RadioButton male, female;
    CheckBox agree;
    Button register, cancel;

    SharedPreferences prefs;

    Dbhelper dbhelper;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);
        dbhelper = new Dbhelper(this);

        id = getIntent().getIntExtra("id",0);

        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        agree = findViewById(R.id.agree);
        register = findViewById(R.id.register);
        cancel = findViewById(R.id.cancel);

        if(id!=0){
            register.setText("Update");
            Userinfo info = dbhelper.getUserInfo(String.valueOf(id));
            username.setText(info.username);
            password.setText(info.password);

            address.setText(info.address);
            if(info.gender.equals("Male")){
                ((RadioButton)findViewById(R.id.male)).setChecked(true);
            }else{
                ((RadioButton)findViewById(R.id.female)).setChecked(true);
            }



        }


        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        register.setOnClickListener(v -> {
            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Username and Password required!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!agree.isChecked()) {
                Toast.makeText(this, "Please agree to the terms!", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("username", user);
            editor.putString("password", pass);
            editor.putString("fullname", fullname.getText().toString());
            editor.putString("email", email.getText().toString());
            editor.putString("address", address.getText().toString());
            editor.putString("phone", phone.getText().toString());
            editor.putString("gender", male.isChecked() ? "Male" : "Female");
            editor.apply();

            ContentValues values = new ContentValues();
            values.put("username",user);
            values.put("password",pass);
            values.put("email",email.getText().toString());
            values.put("gender",male.isChecked() ? "Male" : "Female");
            if(id==0) {
                dbhelper.insertUser(values);
            }else {
                dbhelper.updateUser(id+"",values);
            }

            Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();

            // Redirect to login after registration
//            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//            startActivity(intent);
            finish();
        });

        cancel.setOnClickListener(v -> finish());
    }
}
