package com.example.aaiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main2Activity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private InfoFragment infoFragment;
    private RecentFragment recentFragment;
    private UpdateFragment updateFragment;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bottomNavigationView=findViewById(R.id.bnavbar);
        textView=findViewById(R.id.opttxt);
        infoFragment=new InfoFragment();
        recentFragment=new RecentFragment();
        updateFragment=new UpdateFragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                changeFragment(infoFragment);
                switch(menuItem.getItemId())
                {
                    case R.id.info:
                        changeFragment(infoFragment);
                        textView.setText("");
                        return true;
                    case R.id.recent:
                        textView.setText("");
                        changeFragment(recentFragment);
                        return true;
                    case R.id.update:
                        textView.setText("");
                        changeFragment(updateFragment);
                        return true;
                    default:
                        return false;
                }


            }
        });
    }

    public void changeFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framemain,fragment);
        fragmentTransaction.commit();
    }
}
