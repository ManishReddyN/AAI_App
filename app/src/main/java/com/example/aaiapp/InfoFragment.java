package com.example.aaiapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {
    private Button infobtn;


    Button bt1;
    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View info1= inflater.inflate(R.layout.fragment_info, container, false);
        bt1=info1.findViewById(R.id.infobtn);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity();
            }
        });
        return info1;
    }

    public void launchActivity()
    {
        Intent intent=new Intent(getActivity(),InfoActivity.class);
        startActivity(intent);
    }
}
