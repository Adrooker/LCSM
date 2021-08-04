package com.example.lmcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private Button callsignup, calllogin, callforgot;
    private TextInputLayout usernme, password;
    String userEnteredUsername, userEnteredPassword;

//    private FirebaseAuth firebaseAuth;


    private Boolean validateUsername() {
        String val = usernme.getEditText().getText().toString();
        if (val.isEmpty()) {
            usernme.setError("Field cannot be empty");
            return false;
        } else {
            usernme.setError(null);
            usernme.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        callsignup = findViewById(R.id.signup);
        calllogin = findViewById(R.id.login);
        callforgot = findViewById(R.id.frgt);
        usernme = findViewById(R.id.usernme);                                                          //       usernme = findViewById(R.id.usernme);
        password = findViewById(R.id.paswrd);                                                          //       password = findViewById(R.id.password);
//        firebaseAuth = FirebaseAuth.getInstance();
        callsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, signup.class);
                startActivity(intent);
//                  login();
            }
        });

//        calllogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login.this,Useracces.class);
//                startActivity(intent);
//            }
//        });

        callforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login    .this, forgotpass.class);
                startActivity(intent);
            }
        });

    }

//    private void login(){
//        String email= usernme.getEditText().getText().toString().trim();
//        String paswd= password.getEditText().getText().toString().trim();
//        firebaseAuth.signInWithEmailAndPassword(email,paswd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//            @Override
//            public void onSuccess(AuthResult authResult) {
//
//                Intent intent = new Intent(getApplicationContext(), Useracces.class);
//
//                startActivity(intent);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

    public void loginUser(View view) {

        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {

         userEnteredUsername = usernme.getEditText().getText().toString().trim();
         userEnteredPassword = password.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance("https://lcsm-aa0cc-default-rtdb.firebaseio.com/").getReference("IMP");

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    usernme.setError(null);
                    usernme.setErrorEnabled(false);

                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);


                    if (passwordFromDB.equals(userEnteredPassword)) {
                        password.setError(null);
                        password.setErrorEnabled(false);

                        Intent intent = new Intent(getApplicationContext(), Useracces.class);

                        startActivity(intent);

                    } else {
                        password.setError("wrong password");
                        password.requestFocus();
                    }

                }
                else {
                    usernme.setError("No user exist");
                    usernme.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}