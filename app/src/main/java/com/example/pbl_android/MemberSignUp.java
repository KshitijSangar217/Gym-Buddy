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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MemberSignUp extends AppCompatActivity {

    EditText musername, mpassword, mname;
    Button memsignupbtn;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_sign_up);

        musername = findViewById(R.id.memailans);
        mname = findViewById(R.id.mnameans);
        mpassword = findViewById(R.id.mpasswordans);
        memsignupbtn = findViewById(R.id.membersignupbtn);
        fAuth = FirebaseAuth.getInstance();


        memsignupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memail = musername.getText().toString();
                String mnm = mname.getText().toString();
                String mpass = mpassword.getText().toString();

                if(TextUtils.isEmpty(memail)) {
                    musername.setError("Email is Required !");
                    return;
                }

                if(TextUtils.isEmpty(mpass)) {
                    mpassword.setError("Password is Required !");
                    return ;
                }

                if(TextUtils.isEmpty(mnm)) {
                    mname.setError("Name is Required !");
                    return ;
                }

                if(fAuth.getCurrentUser()!=null) {
                    Intent i1 = new Intent(MemberSignUp.this, MemberMainPage.class);
                    startActivity(i1);
                    finish();
                }

                Intent i = new Intent(MemberSignUp.this, MemberInfo.class);
                i.putExtra("Username", memail);
                i.putExtra("Password", mpass);
                i.putExtra("Name", mnm);
                startActivity(i);
            }
        });

    }
}