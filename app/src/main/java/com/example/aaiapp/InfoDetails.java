package com.example.aaiapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.StringTokenizer;

public class InfoDetails extends AppCompatActivity {
    String message;
    String[] details = new String[40];
    private TextView name,mobile,roll,id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_details);
        name=findViewById(R.id.machinename);
        mobile=findViewById(R.id.machinemobile);
        roll=findViewById(R.id.machineroll);
        id=findViewById(R.id.machineid);

        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("message");
        execute();
        name.setText(details[0]);
        mobile.setText(details[1]);
        roll.setText(details[2]);
        id.setText(details[3]);
    }
    public void execute()
    {
        StringTokenizer st=new StringTokenizer(message,":");
        int i=0;
        while(st.hasMoreTokens())
        {
            details[i]=st.nextToken();
            i+=1;
        }
    }
}
