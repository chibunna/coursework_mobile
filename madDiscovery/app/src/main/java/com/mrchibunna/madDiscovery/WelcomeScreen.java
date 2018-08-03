package com.mrchibunna.madDiscovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import practice.application.sqlitesaveuserdata.R;

public class WelcomeScreen extends AppCompatActivity{
    Button btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomescreen);


        Button btnStart = (Button)findViewById(R.id.btnStart);

        Button btnExit = (Button)findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newintent = new Intent(WelcomeScreen.this, MainActivity.class);
                startActivity(newintent);
            }


        });

    }

}
