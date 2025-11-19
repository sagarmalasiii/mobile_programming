package com.sagar.mobileprogramming;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView username, password, email, address, phone, gender;
    ImageView image;

    Dbhelper dbhelper;
    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        id = getIntent().getStringExtra("id");
        dbhelper = new Dbhelper(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        gender = findViewById(R.id.gender);


        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, RegisterActivity.class);
                intent.putExtra("id",Integer.parseInt(id));

               startActivity(intent);
            }
        });


    }

    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete User!");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbhelper.deleteUser(id);
                finish();

            }
        });
        builder.setNegativeButton("cancel", null);
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayUserinfo();
    }

    public void displayUserinfo() {
        Userinfo info = dbhelper.getUserInfo(id);

        username.setText(info.username);
        password.setText(info.password);
        email.setText(info.email);
        address.setText(info.address);
        gender.setText(info.gender);


    }
}
