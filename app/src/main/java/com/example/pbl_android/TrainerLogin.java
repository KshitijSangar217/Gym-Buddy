package com.example.pbl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TrainerLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_login);
    }

    public void newtrainer (View view)
    {
        Intent i = new Intent(TrainerLogin.this, TrainerSignUp.class);
        startActivity(i);
    }
}