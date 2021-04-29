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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TrainerSignUp extends AppCompatActivity {

    //Frontend variables
    EditText femail, fname, faddress, fphone, fgender, fpassword;
    Button fsignup;
    FirebaseAuth fAuth;
    ProgressBar fprogressbar;


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
        fpassword = findViewById(R.id.passwordans);
        fprogressbar = findViewById(R.id.trainer_progress);

        fAuth = FirebaseAuth.getInstance();

        fsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = femail.getText().toString().trim();
                String name = fname.getText().toString().trim();
                String address = faddress.getText().toString().trim();
                String phone = fphone.getText().toString().trim();
                String gender = fgender.getText().toString().trim();
                String password = fpassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    femail.setError("Email is Required !");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    fname.setError("Name is Required !");
                    return;
                }
                if(TextUtils.isEmpty(address)){
                    faddress.setError("Email is Required !");
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    fphone.setError("Email is Required !");
                    return;
                }
                if(TextUtils.isEmpty(gender)){
                    fgender.setError("Email is Required !");
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
                            TrainerDB.remoteMembers rm = new TrainerDB.remoteMembers("2000", "2000@gmail.com");


                            //Backend Changes
                            TrainerDB trainer = new TrainerDB(email, name, address, phone, gender);

                            //********************here*********************//

                            FirebaseDatabase.getInstance().getReference("Trainers")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())  //Key
                                    .setValue(trainer).addOnCompleteListener(new OnCompleteListener<Void>() { //Value
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //Task of adding a new Trainer data
                                    if(task.isSuccessful()){
                                        //Backend Changes
                                        //Getting Current user ID
                                        String trainer_id = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

                                        FirebaseDatabase.getInstance().getReference("Trainers/"+ trainer_id + "/gymMembers")
                                                .child(gm.gm_id)
                                                .setValue(gm.gm_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    //Frontend Changes
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
                    }
                });
            }
        });
    }
}