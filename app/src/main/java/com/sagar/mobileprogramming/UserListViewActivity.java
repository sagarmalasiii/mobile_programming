package com.sagar.mobileprogramming;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserListViewActivity extends AppCompatActivity {

    ListView listView;
    GridView gridView;

    Dbhelper dbhelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlistview_layout);

        listView = findViewById(R.id.listview);
        dbhelper = new Dbhelper(this);
        gridView = findViewById(R.id.gridView);


        listView.setAdapter(new UserListAdapter(this,dbhelper.getUserList()));
        gridView.setAdapter(new UserListAdapter(this,dbhelper.getUserList()));


    }
}
