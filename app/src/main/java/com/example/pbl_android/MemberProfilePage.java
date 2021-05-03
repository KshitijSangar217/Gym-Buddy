package com.example.pbl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MemberProfilePage extends AppCompatActivity {
    TextInputEditText femail, fname, faddress, fphone, fgender, fage, fheight, fweight, fBg, fTemail;
    Button memberProfileupdatebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_profile_page);

        //Database Connections
        String member_id = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        DatabaseReference dbref;
        dbref = FirebaseDatabase.getInstance().getReference("Members");
        Log.d("Kshitij","1");
        //Hooks
        fname = findViewById(R.id.memberProfileName);
        Log.d("Kshitij","2");
        femail = findViewById(R.id.memberProfileEmail);
        faddress = findViewById(R.id.memberProfileAddress);
        fphone = findViewById(R.id.memberProfilePhone);
        fgender = findViewById(R.id.memberProfileGender);
        fage = findViewById(R.id.memberProfileAge);
        fheight = findViewById(R.id.memberProfileHeight);
        fweight = findViewById(R.id.memberProfileWeight);
        fBg = findViewById(R.id.memberProfileBloodgroup);
        fTemail = findViewById(R.id.memberProfileTrainerMail);
        memberProfileupdatebtn = findViewById(R.id.memberProfileUpdatebtn);
        Log.d("Kshitij","3");

        FirebaseDatabase.getInstance().getReference("Members")
                .child(member_id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("Kshitij","4");

                        fname.setText(snapshot.child("name").getValue(String.class));
                        Log.d("Kshitij","5");
                        Log.d("Kshitij", ": "+ snapshot.child("name").getValue(String.class));
                        femail.setText(snapshot.child("email").getValue(String.class));
                        Log.d("Kshitij", "email: "+ snapshot.child("email").getValue(String.class));
                        faddress.setText(snapshot.child("address").getValue(String.class));
                        Log.d("Kshitij", "contact----: "+ snapshot.child("contactnum").getValue(String.class));
                        fphone.setText(snapshot.child("contactnum").getValue(String.class));
                        Log.d("Kshitij","6");
                        fgender.setText(snapshot.child("gender").getValue(String.class));
                        fage.setText(snapshot.child("age").getValue(String.class));
                        Log.d("Kshitij","7");
                        fheight.setText(snapshot.child("height").getValue(String.class));
                        Log.d("Kshitij","8");
                        fweight.setText(snapshot.child("weight").getValue(String.class));
                        fBg.setText(snapshot.child("bloodgrp").getValue(String.class));
                        Log.d("Kshitij","9");
                        fTemail.setText(snapshot.child("traineremail").getValue(String.class));
                        Log.d("Kshitij","10");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        memberProfileupdatebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //OnClick event
                //Extracting Edited Data
                String email = femail.getText().toString().trim();
                String name = fname.getText().toString().trim();
                String address = faddress.getText().toString().trim();
                String phone = fphone.getText().toString().trim();
                String gender = fgender.getText().toString().trim();
                String age = fage.getText().toString().trim();
                String height = fheight.getText().toString().trim();
                String weight = fweight.getText().toString().trim();
                String bloodgGrp = fBg.getText().toString().trim();
                String temail = fTemail.getText().toString().trim();


                //Updating Profile Data
                dbref.child(member_id).child("name").setValue(name);
                dbref.child(member_id).child("email").setValue(email);
                dbref.child(member_id).child("address").setValue(address);
                dbref.child(member_id).child("contactnum").setValue(phone);
                dbref.child(member_id).child("gender").setValue(gender);
                dbref.child(member_id).child("age").setValue(age);
                dbref.child(member_id).child("height").setValue(height);
                dbref.child(member_id).child("weight").setValue(weight);
                dbref.child(member_id).child("bloodgrp").setValue(bloodgGrp);
                dbref.child(member_id).child("traineremail").setValue(temail);
                Toast.makeText(MemberProfilePage.this, "Profile Updated Successfully !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}