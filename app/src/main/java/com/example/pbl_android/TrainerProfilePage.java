package com.example.pbl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;

public class TrainerProfilePage extends AppCompatActivity {
    TextInputEditText femail, fname, faddress, fphone, fgender, fgpayid, fcertification, fdescription, fonlinefee, fofflinefee, fpassword;
    CheckBox fCBonline, fCBoffline;
    Button trainerProfileupdatebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_profile_page);

        //Database Connections
        //FirebaseAuth fAuth = FirebaseAuth.getInstance();
        String trainer_id = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        DatabaseReference dbref;
        dbref = FirebaseDatabase.getInstance().getReference("Trainers");

        //Hooks
        fname = findViewById(R.id.trainerProfileName);
        femail = findViewById(R.id.trainerProfileEmail);
        faddress = findViewById(R.id.trainerProfileAddress);
        fphone = findViewById(R.id.trainerProfilePhone);
        fgender = findViewById(R.id.trainerProfileGender);
        fgpayid = findViewById(R.id.trainerProfileGpay);
        fcertification = findViewById(R.id.trainerProfileCertification);
        fdescription = findViewById(R.id.trainerProfileDescription);
        fonlinefee = findViewById(R.id.trainerProfileOnlineFee);
        fofflinefee = findViewById(R.id.trainerProfileOfflineFee);
        fCBonline = findViewById(R.id.trainerProfileOnlinebtn);
        fCBoffline = findViewById(R.id.trainerProfileOfflinebtn);
        trainerProfileupdatebtn = findViewById(R.id.trainerProfileUpdatebtn);



        FirebaseDatabase.getInstance().getReference("Trainers")
                .child(trainer_id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        fname.setText(snapshot.child("name").getValue(String.class));
                        Log.d("Kshitij", ": "+ snapshot.child("name").getValue(String.class));
                        femail.setText(snapshot.child("email").getValue(String.class));
                        Log.d("Kshitij", "email: "+ snapshot.child("email").getValue(String.class));
                        faddress.setText(snapshot.child("address").getValue(String.class));
                        Log.d("Kshitij", "address: "+ snapshot.child("address").getValue(String.class));
                        fphone.setText(snapshot.child("phone").getValue(String.class));
                        fgender.setText(snapshot.child("gender").getValue(String.class));
                        fgpayid.setText(snapshot.child("gpayid").getValue(String.class));
                        fcertification.setText(snapshot.child("certification").getValue(String.class));
                        fdescription.setText(snapshot.child("description").getValue(String.class));
                        fonlinefee.setText(snapshot.child("onlinefee").getValue(String.class));
                        fofflinefee.setText(snapshot.child("offlinefee").getValue(String.class));
                        if(snapshot.child("online").getValue().toString() == "true") {
                            fCBonline.setChecked(true);
                        }
                        else{
                            fCBonline.setChecked(false);
                        }

                        if(snapshot.child("offline").getValue().toString() == "true") {
                            fCBoffline.setChecked(true);
                        }
                        else{
                            fCBoffline.setChecked(false);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        trainerProfileupdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OnClick event
                //Extracting Edited Data
                String email = femail.getText().toString().trim();
                String name = fname.getText().toString().trim();
                String address = faddress.getText().toString().trim();
                String phone = fphone.getText().toString().trim();
                String gender = fgender.getText().toString().trim();
                String gpayid = fgpayid.getText().toString().trim();
                String certification = fcertification.getText().toString().trim();
                String description = fdescription.getText().toString().trim();
                String onlinefee = fonlinefee.getText().toString().trim();
                String offlinefee = fofflinefee.getText().toString().trim();
                Boolean CBonline = false, CBoffline = false;

                if(fCBonline.isChecked()) {
                    CBonline = true;
                }

                if(fCBoffline.isChecked()) {
                    CBoffline = true;
                }

                //Updating Profile Data
                dbref.child(trainer_id).child("name").setValue(name);
                dbref.child(trainer_id).child("email").setValue(email);
                dbref.child(trainer_id).child("address").setValue(address);
                dbref.child(trainer_id).child("phone").setValue(phone);
                dbref.child(trainer_id).child("gender").setValue(gender);
                dbref.child(trainer_id).child("gpayid").setValue(gpayid);
                dbref.child(trainer_id).child("certification").setValue(certification);
                dbref.child(trainer_id).child("description").setValue(description);
                dbref.child(trainer_id).child("onlinefee").setValue(onlinefee);
                dbref.child(trainer_id).child("offlinefee").setValue(offlinefee);
                dbref.child(trainer_id).child("online").setValue(CBonline);
                dbref.child(trainer_id).child("offline").setValue(CBoffline);
                Toast.makeText(TrainerProfilePage.this, "Data Updated Successfully !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}