package com.example.findvendor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signIn extends AppCompatActivity {

    private FirebaseAuth auth;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    public void signupActivity(View view){
        Intent intent = new Intent(this, VendorRegistation.class);
        startActivity(intent);
    }
    public void LoginActivity(View view){
        Intent intent2 = new Intent(this, CustomerProfile.class);
        startActivity(intent2);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        EditText emaillogin = (EditText) findViewById(R.id.loginEmail);
        EditText passlogin = (EditText) findViewById(R.id.loginPassword);
        Button loginbtn = findViewById(R.id.loginBtn);
        auth=FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2 = emaillogin.getText().toString();
                String pass2 = passlogin.getText().toString();
                if(TextUtils.isEmpty(email2)||TextUtils.isEmpty(pass2)){
                    Toast.makeText(signIn.this, "Please enter required data", Toast.LENGTH_SHORT).show();
                }
                else{
                    loginfunction(email2,pass2);
                }
            }
        });
    }
    private void loginfunction(String email,String password){
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(signIn.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                user = FirebaseAuth.getInstance().getCurrentUser();
                reference = FirebaseDatabase.getInstance().getReference("users");
                userID = user.getUid();

                reference.child(userID).child("iscustomer").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int usertype = snapshot.getValue(Integer.class);
                        if(usertype == 0){
                            startActivity(new Intent(getApplicationContext(), VendorProfile.class));
                        }

                        if(usertype == 1){
                            startActivity(new Intent(getApplicationContext(), CustomerProfile.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(signIn.this, "can't find text ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
