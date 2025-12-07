package com.sagar.mobileprogramming;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserlistActivity extends AppCompatActivity {

    LinearLayout container;

    Dbhelper dbhelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist_layout);
        dbhelper = new Dbhelper(this);
        container = findViewById(R.id.container);

    }

    @Override
    protected void onResume() {
        super.onResume();
        displayUsers();
    }

    public void displayUsers() {
        ArrayList<Userinfo> list = dbhelper.getUserList();
        container.removeAllViews();
        for (Userinfo info : list) {

            View view = LayoutInflater.from(this).inflate(R.layout.item_layout, null);
            TextView username = view.findViewById(R.id.username);
            ImageView imageView = view.findViewById(R.id.image);

            username.setText(info.username);
            if (info.image != null)
                imageView.setImageBitmap(RegisterActivity.byteArrayToBitmap(info.image));


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserlistActivity.this, DetailActivity.class);
                    intent.putExtra("id", info.id);
                    startActivity(intent);

                }
            });
            container.addView(view);


        }
    }
}
