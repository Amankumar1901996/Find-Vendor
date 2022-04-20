package com.example.findvendor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerRegister extends AppCompatActivity {

    TextView username , fullname, cityname, address, email, password;
    FirebaseAuth auth;
    FirebaseDatabase database;
    int iscustomer = 1;

    public void signinActivity(View view){
        Intent intent = new Intent(getApplicationContext(),signIn.class);
        startActivity(intent);
    }

    public void vendorRegisteration(View view){
        Intent intent = new Intent(getApplicationContext(), VendorRegistation.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);
        username = (TextView) findViewById(R.id.username);
        fullname = (TextView) findViewById(R.id.fullname);
        cityname = (TextView) findViewById(R.id.cityname);
        address = (TextView) findViewById(R.id.address);
        email = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.signuppassword);
        auth=FirebaseAuth.getInstance();
        ProgressBar progressBar = findViewById(R.id.progressBar);

        Button cregisterbtn = findViewById(R.id.cregisterBtn);
        cregisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String S_username=username.getText().toString().trim();
                String S_fullname=fullname.getText().toString().trim();
                String S_cityname = cityname.getText().toString().trim();
                String S_address = address.getText().toString().trim();
                String S_email=email.getText().toString().trim();
                String S_password=password.getText().toString();


                if(S_username.isEmpty()){
                    username.setError("Please enter valid username");
                    username.requestFocus();
                    return;

                } if(S_fullname.isEmpty()){
                    fullname.setError("Please enter full name");
                    fullname.requestFocus();
                    return;

                } if (S_cityname.isEmpty()) {
                    cityname.setError("Please enter valid institution name");
                    cityname.requestFocus();
                    return;

                } if (S_address.isEmpty()) {
                    address.setError("Please enter valid roll no. ");
                    address.requestFocus();
                    return;

                } if(S_email.isEmpty()){
                    email.setError("Please enter valid email ");
                    email.requestFocus();
                    return;

                } if(S_password.isEmpty()){
                    password.setError("");
                    password.requestFocus();
                    return;

                }
                if(S_password.length() < 6){
                    password.setError("Min characters should be 6 !");
                    password.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                auth.createUserWithEmailAndPassword(S_email, S_password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Log.i("click r","inside if of auth");
                                    CustomerDataHolder customerDataHolder = new CustomerDataHolder(S_username,S_fullname,S_cityname,S_address,S_email,S_password, iscustomer);

                                    FirebaseDatabase.getInstance().getReference("users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(customerDataHolder).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                progressBar.setVisibility(View.INVISIBLE);
                                                Log.i("realtime","inside if of realtime");
                                                Toast.makeText(CustomerRegister.this, "successfully registered", Toast.LENGTH_SHORT).show();
                                                Intent activity2Intent = new Intent(getApplicationContext(), signIn.class);
                                                startActivity(activity2Intent);
                                            }
                                            else{
                                                Toast.makeText(CustomerRegister.this, "fail to register", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                }
                                else{
                                    Log.e("raj" , task.toString());
                                    Toast.makeText(CustomerRegister.this, "failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                /*
                Bundle bundle=new Bundle();

                Intent intentR = new Intent(getApplicationContext(),ProfileActivity.class);
                intentR.putExtra("S_user",S_username);
                intentR.putExtra("S_full",S_fullname);
                intentR.putExtra("S_institute",S_institution);
                intentR.putExtra("S_roll",S_rollno);
                intentR.putExtra("S_e",S_email);

                intentR.putExtras(bundle);
                startActivity(intentR);
                */

            }
        });




    }
}