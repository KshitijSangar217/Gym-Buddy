package com.example.pbl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MemberInfo extends AppCompatActivity {

    EditText traineremail, maddress, mcontactno, mgender, mage, mbloodgrp, mheight, mweight;
    TextView temptxt;
    RadioGroup plangrp;
    RadioButton onlinebtn, offlinebtn;
    Button creatememacc;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info);

        traineremail = findViewById(R.id.traineremail);
        maddress = findViewById(R.id.addrans);
        mcontactno = findViewById(R.id.contactans);
        mgender = findViewById(R.id.genderans);
        mage = findViewById(R.id.ageans);
        mbloodgrp = findViewById(R.id.bloodgrpans);
        mheight = findViewById(R.id.heightans);
        mweight = findViewById(R.id.weightans);
        plangrp = findViewById(R.id.rdbtngrp);
        onlinebtn = findViewById(R.id.rdbtnonline);
        offlinebtn = findViewById(R.id.rdbtnoffline);
        creatememacc = findViewById(R.id.creatememaccbtn);
        temptxt = findViewById(R.id.temptxt);
        fAuth = FirebaseAuth.getInstance();

        creatememacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent currintent = getIntent();
                String mememail = currintent.getExtras().get("Username").toString();
                String mempassword = currintent.getExtras().get("Password").toString();
                String memname = currintent.getExtras().get("Name").toString();
                String tremail = traineremail.getText().toString();
                String memaddress = maddress.getText().toString();
                String memcontactno = mcontactno.getText().toString();
                String memgender =mgender.getText().toString();
                String memage = mage.getText().toString();
                String membloodgrp = mbloodgrp.getText().toString();
                String memheight = mheight.getText().toString();
                String memweight = mweight.getText().toString();
                String memplan;
                /*String result = "Usernm : " + mememail + "\n";
                result = result +  "Password : " + mempassword + "\n";
                result = result +  "Name : " + memname + "\n";*/

                int selectid = plangrp.getCheckedRadioButtonId();
                if(selectid == onlinebtn.getId()) {
                    memplan = onlinebtn.getText().toString();
                }
                else {
                    memplan = offlinebtn.getText().toString();
                }

                //result = result +  "Plan : " + memplan + "\n";


                if(TextUtils.isEmpty(tremail)) {
                    traineremail.setError("Trainer Email is Required !");
                    return;
                }
                //result = result +  "Trainer Mail : " + memname + "\n";

                if(TextUtils.isEmpty(memaddress)) {
                    maddress.setError("Address is Required !");
                    return ;
                }

                if(TextUtils.isEmpty(memcontactno)) {
                    maddress.setError("Contact Number is Required !");
                    return ;
                }

                if(TextUtils.isEmpty(memgender)) {
                    mgender.setError("Gender is Required !");
                    return ;
                }

                if(TextUtils.isEmpty(memage)) {
                    mage.setError("Age is Required !");
                    return;
                }

                if(TextUtils.isEmpty(membloodgrp)) {
                    mbloodgrp.setError("Blood Group is Required !");
                    return ;
                }

                if(TextUtils.isEmpty(memheight)) {
                    mheight.setError("Height is Required !");
                    return ;
                }

                if(TextUtils.isEmpty(memweight)) {
                    mweight.setError("Weight is Required !");
                    return;
                }

                if(memplan.length()==0) {
                    Toast.makeText(MemberInfo.this, "Please Select Plan", Toast.LENGTH_LONG).show();
                    return;
                }

                fAuth.createUserWithEmailAndPassword(mememail, mempassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            MemberDB mem = new MemberDB(mememail, mempassword, memname, tremail, memaddress, memplan, memgender, membloodgrp, Integer.parseInt(memage), Integer.parseInt(memheight), Integer.parseInt(memweight));
                            FirebaseDatabase.getInstance().getReference("Members")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(mem).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(MemberInfo.this, "Registration Successful !", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(MemberInfo.this, MemberMainPage.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(MemberInfo.this, "Registration Failed ! Please try again !", Toast.LENGTH_LONG).show();
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