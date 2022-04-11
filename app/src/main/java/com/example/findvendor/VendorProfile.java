package com.example.findvendor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VendorProfile extends AppCompatActivity {

    Button myroute;
    public void createroute(View view){
        startActivity(new Intent(getApplicationContext(), VendorRoute.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_profile);

        myroute = findViewById(R.id.myroutebtn);

        myroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), VendorRoute.class));
            }
        });

    }


}