package com.sagar.mobileprogramming;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TopFragment extends Fragment {

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvRegister;
    CheckBox rememberMe;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_form, null);

        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvRegister = view.findViewById(R.id.tvRegister);
        rememberMe = view.findViewById(R.id.rememberMe);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Value = etUsername.getText().toString();
                ((FragmentExampleActivity) getActivity()).setBottomFragmentWelcomeValue(Value);

//                startActivity(new Intent(getActivity(), HomeActivity.class));
            }
        });
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                 ((FragmentExampleActivity) getActivity()).setBottomFragmentWelcomeValue(s.toString());

            }
        });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
