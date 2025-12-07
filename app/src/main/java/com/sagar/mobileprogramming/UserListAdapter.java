package com.sagar.mobileprogramming;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserListAdapter extends ArrayAdapter<Userinfo> {

    Context context;

    public UserListAdapter(@NonNull Context context,  ArrayList<Userinfo>list) {
        super(context, 0,list);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
        TextView username = view.findViewById(R.id.username);
        ImageView imageView = view.findViewById(R.id.image);
        Userinfo info = getItem(position);
        username.setText(info.username);
        if (info.image != null)
            imageView.setImageBitmap(RegisterActivity.byteArrayToBitmap(info.image));


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", info.id);
                context.startActivity(intent);

            }
        });

        return view;
    }
}
