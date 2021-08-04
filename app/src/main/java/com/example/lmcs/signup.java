package com.example.lmcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    TextInputLayout rname, rusername, remail, rphonenum, rpwd;
    Button callsignup, calllogin, callback;
    String userEnteredRUsername, userEnteredRPassword,userEnteredREmail;
    FirebaseDatabase rootNode;
    DatabaseReference reference2,reference1;




    private Boolean validateName() {
        String val = rname.getEditText().getText().toString();

        if (val.isEmpty()) {
            rname.setError("Field cannot be empty");
            return false;
        } else {
            rname.setError(null);
            rname.setErrorEnabled(false);
            return true;

        }
    }

    private Boolean validateUsername() {
        String val = rusername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            rusername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            rusername.setError("Username is long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            rusername.setError("No white space allowed");
            return false;
        } else {
            rusername.setError(null);
            rusername.setErrorEnabled(false);
            return true;

        }
    }

    private Boolean validateEmail() {
        String val = remail.getEditText().getText().toString();
        String emailpat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            remail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailpat)) {
            remail.setError("Invalid Mail ID");
            return false;
        } else {
            remail.setError(null);
            remail.setErrorEnabled(false);
            return true;

        }
    }

    private Boolean validatephonenum() {
        String val = rphonenum.getEditText().getText().toString();
        if (val.isEmpty()) {
            rphonenum.setError("Field cannot be empty");
            return false;
        } else {
            rphonenum.setError(null);
            rphonenum.setErrorEnabled(false);
            return true;

        }
    }

    private Boolean validatepassword() {
        String val = rpwd.getEditText().getText().toString();
        String passval = "^" + "(?=.*[a-zA-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$)" + ".{4,}" + "$";

        if (val.isEmpty()) {
            rpwd.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passval)) {
            rpwd.setError("Weak Password");
            return false;
        } else {
            rpwd.setError(null);
            rpwd.setErrorEnabled(false);
            return true;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        callback = findViewById(R.id.otpres);
        callsignup = findViewById(R.id.submit);
        rname = findViewById(R.id.name);
        rusername = findViewById(R.id.usernme);
        remail = findViewById(R.id.email);
        rphonenum = findViewById(R.id.phonenum);
        rpwd = findViewById(R.id.pwd);


//        callsignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rootNode = FirebaseDatabase.getInstance("https://lcsm-aa0cc-default-rtdb.firebaseio.com/");
//                reference = rootNode.getReference("IMP");
//
//                if (!validateName() | !validatepassword() | !validateEmail() | !validatephonenum() | !validateUsername()) {
//                    return;
//                }
//
//                String name = rname.getEditText().getText().toString();
//                String username = rusername.getEditText().getText().toString();
//                String email = remail.getEditText().getText().toString();
//                String phoneNo = rphonenum.getEditText().getText().toString();
//                String password = rpwd.getEditText().getText().toString();
//
//                UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
//
//                reference.child(phoneNo).setValue(helperClass);
//
//            }
//        });

        callback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, Login.class);
                startActivity(intent);
            }
        });

    }


    public void registerUser(View view) {

        userEnteredRUsername = rusername.getEditText().getText().toString().trim();
        userEnteredRPassword = rpwd.getEditText().getText().toString().trim();
        userEnteredREmail = remail.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance("https://lcsm-aa0cc-default-rtdb.firebaseio.com/").getReference("IMP");


        if (!validateName() | !validatepassword() | !validateEmail() | !validatephonenum() | !validateUsername()) {
            return;
        }

        rootNode = FirebaseDatabase.getInstance("https://lcsm-aa0cc-default-rtdb.firebaseio.com/");
        reference1 = rootNode.getReference("IMP");
        reference2 = rootNode.getReference("Valid");

        String name = rname.getEditText().getText().toString();
        String username = rusername.getEditText().getText().toString();
        String email = remail.getEditText().getText().toString();
        String phoneNo = rphonenum.getEditText().getText().toString();
        String password = rpwd.getEditText().getText().toString();

        UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
        reference1.child(username).setValue(helperClass);
        reference2.child(phoneNo).setValue(helperClass);


    }
}