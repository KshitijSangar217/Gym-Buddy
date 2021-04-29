package com.example.pbl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class TrainerLogin extends AppCompatActivity {

    EditText txtin_email, txtin_password;
    Button btn_signin;
    FirebaseAuth fAuth;
    ProgressBar fprogressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_login);

        //Hooking up
        txtin_email = findViewById(R.id.emailans);
        txtin_password = findViewById(R.id.passwordans);
        btn_signin = findViewById(R.id.signin);
        fAuth = FirebaseAuth.getInstance();
        fprogressbar = findViewById(R.id.trainer_progress);

        //Onclick Event
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtin_email.getText().toString().trim();
                String password = txtin_password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    txtin_email.setError("Email is Required !");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    txtin_password.setError("Password is Required !");
                    return;
                }

                fprogressbar.setVisibility(View.VISIBLE);
                //Signin Authentication
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(TrainerLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            fprogressbar.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(getApplicationContext(), TrainerMainPage.class));
                        }
                        else {
                            Toast.makeText(TrainerLogin.this, "Error ! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }

    public void newtrainer (View view)
    {
        Intent i = new Intent(TrainerLogin.this, TrainerSignUp.class);
        startActivity(i);
    }
}