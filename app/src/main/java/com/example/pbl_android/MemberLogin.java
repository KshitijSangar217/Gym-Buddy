package com.example.pbl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class MemberLogin extends AppCompatActivity {

    EditText emailtxt, passtxt;
    FirebaseAuth fAuth;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);

        emailtxt = findViewById(R.id.emailans);
        passtxt = findViewById(R.id.passwordans);
        loginbtn = findViewById(R.id.signinbtn);
        fAuth = FirebaseAuth.getInstance();


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailtxt.getText().toString().trim();
                String password = passtxt.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    emailtxt.setError("Email ID required !");
                    return ;
                }

                if(TextUtils.isEmpty(password)) {
                    passtxt.setError("Password required !");
                    return ;
                }

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(MemberLogin.this, "Login Successful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MemberLogin.this, MemberMainPage.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(MemberLogin.this, "Login Unsucessful. Please try again !", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    public void newmember (View view)
    {
        Intent i = new Intent(MemberLogin.this, MemberSignUp.class);
        startActivity(i);
    }
}