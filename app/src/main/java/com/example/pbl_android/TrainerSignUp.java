    package com.example.pbl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TrainerSignUp extends AppCompatActivity {

    //Frontend variables
    TextInputLayout femail, fname, faddress, fphone, fgender, fgpayid, fcertification, fdescription, fonlinefee, fofflinefee, fpassword;
    Button fsignup;
    FirebaseAuth fAuth;
    ProgressBar fprogressbar;
    CheckBox chbxonline, chbxoffline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_sign_up);

        //Hooking up
        fsignup = findViewById(R.id.signin);
        femail = findViewById(R.id.emailans);
        fname = findViewById(R.id.nameans);
        faddress = findViewById(R.id.addrans);
        fphone = findViewById(R.id.contactans);
        fgender = findViewById(R.id.genderans);
        fgpayid = findViewById(R.id.gpayidans);
        fcertification = findViewById(R.id.certificationans);
        fdescription = findViewById(R.id.descriptionans);
        fonlinefee = findViewById(R.id.onlinefee);
        fofflinefee = findViewById(R.id.offlinefee);
        fpassword = findViewById(R.id.passwordans);
        chbxoffline = findViewById(R.id.offlinebtn);
        chbxonline = findViewById(R.id.onlinebtn);
        fprogressbar = findViewById(R.id.trainer_progress);

        fAuth = FirebaseAuth.getInstance();

        fsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = femail.getEditText().getText().toString().trim();
                String name = fname.getEditText().getText().toString().trim();
                String address = faddress.getEditText().getText().toString().trim();
                String phone = fphone.getEditText().getText().toString().trim();
                String gender = fgender.getEditText().getText().toString().trim();
                String gpayid = fgpayid.getEditText().getText().toString().trim();
                String certification = fcertification.getEditText().getText().toString().trim();
                String description = fdescription.getEditText().getText().toString().trim();
                String onlinefee = fonlinefee.getEditText().getText().toString().trim();
                String offlinefee = fofflinefee.getEditText().getText().toString().trim();
                String password = fpassword.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    femail.setError("Email is Required !");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    fname.setError("Name is Required !");
                    return;
                }
                if(TextUtils.isEmpty(address)){
                    faddress.setError("Adress is Required !");
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    fphone.setError("Phone No is Required !");
                    return;
                }
                if(TextUtils.isEmpty(gender)){
                    fgender.setError("Gender is Required !");
                    return;
                }
                if(TextUtils.isEmpty(gpayid)){
                    fgender.setError("GPayId is Required !");
                    return;
                }
                if(TextUtils.isEmpty(description)){
                    fgender.setError("Description is Required !");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    fpassword.setError("Password is Required !");
                    return;
                }

                if(fAuth.getCurrentUser() != null){
                    startActivity(new Intent(getApplicationContext(), TrainerMainPage.class));
                    finish();
                }

                fprogressbar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            //Creating the gym members list and the remote members list.
                            TrainerDB.gymMembers gm = new TrainerDB.gymMembers("1000", "1000@gmail.com");

                            boolean offline = false;
                            boolean online = false;
                            if(chbxoffline.isChecked()) {
                                offline = true;
                            }

                            if(chbxonline.isChecked()) {
                                online = true;
                            }

                            //Backend Changes
                            TrainerDB trainer = new TrainerDB(email, name, address, phone, gender, gpayid, certification, description, onlinefee, offlinefee, online, offline);

                            //********************here*********************//

                            FirebaseDatabase.getInstance().getReference("Trainers")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())  //Key
                                    .setValue(trainer).addOnCompleteListener(new OnCompleteListener<Void>() { //Value
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //Task of adding a new Trainer data
                                    if(task.isSuccessful()){
                                        Toast.makeText(TrainerSignUp.this, "Registration Successful !", Toast.LENGTH_SHORT).show();
                                        fprogressbar.setVisibility(View.INVISIBLE);
                                        startActivity(new Intent(getApplicationContext(), TrainerMainPage.class));
                                    }
                                    else {
                                        Toast.makeText(TrainerSignUp.this, "Error ! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}