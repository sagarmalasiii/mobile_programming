package com.sagar.mobileprogramming;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class TabUsingFragmentActivity extends AppCompatActivity {

    TextView tab1, tab2,tab3;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_using_fragment_layout);

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,new TopFragment()).commit();
        tab1.setTextColor(Color.RED);
    }

    public void tabClickListener(View view){
        int id = view.getId();
        tab1.setTextColor(Color.BLACK);
        tab2.setTextColor(Color.BLACK);
        tab3.setTextColor(Color.BLACK);
        Fragment fragment=new TopFragment();
        if(id==R.id.tab1){
            tab1.setTextColor(Color.RED);
            fragment=new TopFragment();
        }else if(id==R.id.tab2){
            tab2.setTextColor(Color.RED);
            fragment=new BottomFragment();
        }else if(id==R.id.tab3){
            tab3.setTextColor(Color.RED);
            fragment=new CenterFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,fragment).commit();

    }
}
