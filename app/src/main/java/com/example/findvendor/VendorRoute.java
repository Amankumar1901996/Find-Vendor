package com.example.findvendor;


import androidx.annotation.NonNull;
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
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

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

                        //c1
                        VendorRouteModel vendorRouteModel = new VendorRouteModel(location,time);
                        FirebaseDatabase.getInstance().getReference("users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child("route").push().setValue(vendorRouteModel)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(),"inserted Successfully", Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(),"could not inserted ", Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                    }
                                });

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
        FirebaseRecyclerOptions<VendorRouteModel> options =
                new FirebaseRecyclerOptions.Builder<VendorRouteModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child("route"), VendorRouteModel.class)
                        .build();
        adapter = new RouteLocationAdapter(options);

        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }




}