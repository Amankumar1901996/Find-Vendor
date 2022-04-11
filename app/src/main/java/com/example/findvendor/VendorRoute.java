package com.example.findvendor;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class VendorRoute extends AppCompatActivity {

    RouteLocationAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<VendorRouteModel> arrlocation = new ArrayList<>();
    FloatingActionButton addroutebtn;

    TimePicker simpleTimePicker;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_route);

        recyclerView = findViewById(R.id.completeroute);
        addroutebtn = findViewById(R.id.addroutebtn);

        addroutebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(VendorRoute.this);
                dialog.setContentView(R.layout.route_dialogbox);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                //fatch location
                EditText addlocationtxt = dialog.findViewById(R.id.addlocationtxt);
                //fatch time
                simpleTimePicker = dialog.findViewById(R.id.simpleTimePicker);

                simpleTimePicker.setIs24HourView(false); // used to display AM/PM mode
                // perform set on time changed listener event
                simpleTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        // display a toast with changed values of time picker
                        Toast.makeText(getApplicationContext(), hourOfDay + "  " + minute, Toast.LENGTH_SHORT).show();
                        String h=Integer.toString(hourOfDay);
                        String m=Integer.toString(minute);
                        time = h +" : "+ m;
                    }
                });

                Button action = dialog.findViewById(R.id.action);

                action.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //apply check later
                        String location = addlocationtxt.getText().toString().trim();
                        //get time

                        arrlocation.add(new VendorRouteModel(location, time));
                        adapter.notifyItemInserted(arrlocation.size()-1);
                        recyclerView.scrollToPosition(arrlocation.size()-1);

                        dialog.dismiss();

                    }
                });

                dialog.show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RouteLocationAdapter(this, arrlocation);

        recyclerView.setAdapter(adapter);
    }


}