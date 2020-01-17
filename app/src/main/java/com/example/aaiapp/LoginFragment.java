package com.example.aaiapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    EditText lid,pwd;
    Button loginbt;
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
        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity();

            }
        });
        return v1;
    }
    public void launchActivity()
    {
        Intent intent=new Intent(getActivity(), Main2Activity.class);
        startActivity(intent);
    }

}
