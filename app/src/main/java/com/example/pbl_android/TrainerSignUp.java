package com.example.pbl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TrainerSignUp extends AppCompatActivity {

    EditText username, pwd;
    Button signup;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_sign_up);

        //Hooking up
        signup = findViewById(R.id.signin);
        username = findViewById(R.id.emailans);
        pwd = findViewById(R.id.passwordans);

        fAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString().trim();
                String password = pwd.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    username.setError("Email is Required !");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    pwd.setError("Password is Required !");
                    return;
                }

                if(fAuth.getCurrentUser() != null){
                    startActivity(new Intent(getApplicationContext(), TrainerMainPage.class));
                    finish();
                }

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(TrainerSignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), TrainerMainPage.class));
                        }
                        else {
                            Toast.makeText(TrainerSignUp.this, "Error ! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}