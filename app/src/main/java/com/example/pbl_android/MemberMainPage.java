package com.example.pbl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MemberMainPage extends AppCompatActivity {
    Button meetingDetails, dietbtn, planbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_main_page);

        meetingDetails = findViewById(R.id.meetingdetailsbtn);
        dietbtn = findViewById(R.id.dietbtn);
        planbtn = findViewById(R.id.planbtn);
        meetingDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MemberMainPage.this, MemberMyMeetings.class);
                startActivity(i);
            }
        });

        dietbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MemberMainPage.this, DietActivity.class);
                startActivity(i);
            }
        });

        planbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MemberMainPage.this, PlanActivity.class);
                startActivity(i);
            }
        });
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),SelectType.class));
        finish();
    }

    public void openMyProfile(View view) {
        Intent i = new Intent(MemberMainPage.this, MemberProfilePage.class);
        startActivity(i);
    }

    public void openMyTrainer(View view) {
        Intent i = new Intent(MemberMainPage.this, TrainerProfileInMemberDshBoard.class);
        startActivity(i);
    }
}