package com.example.aaiapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;



public class LoginFragment extends Fragment {

    private EditText lid, pwd;
    private Button loginbt;
    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v1=inflater.inflate(R.layout.fragment_login,container,false);
        lid=v1.findViewById(R.id.login1);
        pwd=v1.findViewById(R.id.pass1);
        loginbt=v1.findViewById(R.id.btn1);
        loginbt.setOnClickListener(v -> {
            int c = 1;
            String username = lid.getText().toString();
            String password = pwd.getText().toString();
            String type = "login";
            BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
            backgroundWorker.execute(type, username, password);
        });
        return v1;
    }

}
