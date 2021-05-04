package com.example.pbl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;

public class TrainerProfileInMemberDshBoard extends AppCompatActivity {
    TextView femail, fname, faddress, fphone, fgender, fgpayid, fcertification, fdescription, fonlinefee, fofflinefee, fpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_profile_in_member_dsh_board);

        //Database Connections
        //FirebaseAuth fAuth = FirebaseAuth.getInstance();
        String member_id = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        DatabaseReference dbref;
        dbref = FirebaseDatabase.getInstance().getReference("Members");

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

        //Code to get the "traineremail"
        FirebaseDatabase.getInstance().getReference("Members")
                .child(member_id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Getting "traineremail"
                        String traineremail = snapshot.child("traineremail").getValue(String.class);
                        Log.d("Kshitij","Dday: traineremail="+traineremail);

                        //Code to get the trainer details using the "traineremail"
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Trainers");
                        Query checkuser = ref.orderByChild("email").equalTo(traineremail);
                        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot tsnapshot) {
                                if (tsnapshot.exists())
                                {
                                    for (DataSnapshot snapsht : tsnapshot.getChildren())
                                    {
                                        Log.d("Kshitij","Dday: snapshot('name')"+ snapsht.child("name").getValue());
                                        Log.d("Kshitij","Dday: snapshot('name')"+ snapsht.child("name").getValue(String.class));

                                        fname.setText(snapsht.child("name").getValue(String.class));
                                        Log.d("Kshitij", ": "+ snapsht.child("name").getValue(String.class));
                                        femail.setText(snapsht.child("email").getValue(String.class));
                                        Log.d("Kshitij", "email: "+ snapsht.child("email").getValue(String.class));
                                        faddress.setText(snapsht.child("address").getValue(String.class));
                                        Log.d("Kshitij", "address: "+ snapsht.child("address").getValue(String.class));
                                        fphone.setText(snapsht.child("phone").getValue(String.class));
                                        fgender.setText(snapsht.child("gender").getValue(String.class));
                                        fgpayid.setText(snapsht.child("gpayid").getValue(String.class));
                                        fcertification.setText(snapsht.child("certification").getValue(String.class));
                                        fdescription.setText(snapsht.child("description").getValue(String.class));
                                        fonlinefee.setText(snapsht.child("onlinefee").getValue(String.class));
                                        fofflinefee.setText(snapsht.child("offlinefee").getValue(String.class));
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        /*trainerProfileupdatebtn.setOnClickListener(new View.OnClickListener() {
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





                //NO NEED--------------------------------------------------------------------------------
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
                Toast.makeText(TrainerProfileInMemberDshBoard.this, "Profile Updated Successfully !", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}