package com.example.pbl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MemberLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);
    }

    public void newmember (View view)
    {
        Intent i = new Intent(MemberLogin.this, MemberSignUp.class);
        startActivity(i);
    }
}