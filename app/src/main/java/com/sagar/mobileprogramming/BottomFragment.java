package com.sagar.mobileprogramming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BottomFragment extends Fragment {

    TextView tvWelcome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_activity, null);
        tvWelcome = view.findViewById(R.id.tvWelcome);

        return view;
    }

    public void setTvWelcome(String value){
        tvWelcome.setText("Welcome "+value);
    }
}
