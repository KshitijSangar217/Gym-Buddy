package com.example.pbl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class TrainerMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_main_page);
    }

    public void meetingdetails (View view) {
        Intent i = new Intent(TrainerMainPage.this, TrainerMeeting.class);
        startActivity(i);
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),SelectType.class));
        finish();
    }

    public void membersList(View view) {
        Intent i = new Intent(TrainerMainPage.this, TrainersEnrolledMemberList.class);
        startActivity(i);
    }

    public void openMyProfile(View view) {
        Intent i = new Intent(TrainerMainPage.this, TrainerProfilePage.class);
        startActivity(i);
    }
}