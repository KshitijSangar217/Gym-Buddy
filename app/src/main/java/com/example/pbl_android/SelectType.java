package com.example.pbl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);
    }

    public void trainerloginbtn (View view)
    {
        Intent i = new Intent(SelectType.this, TrainerLogin.class);
        startActivity(i);
    }

    public void memberloginbtn (View view)
    {
        Intent i = new Intent(SelectType.this, MemberLogin.class);
        startActivity(i);
    }
}