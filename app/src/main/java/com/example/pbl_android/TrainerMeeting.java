package com.example.pbl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class TrainerMeeting extends AppCompatActivity {

    Button mymeetings, schedulemeeting, addmeeting;
    TextInputLayout meetingname, meetinglink, meetingdate, startTime, endTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_meeting);

        mymeetings = findViewById(R.id.mymeetings);
        schedulemeeting = findViewById(R.id.schedulemeeting);
        addmeeting = findViewById(R.id.addmeeting);

        meetingname = findViewById(R.id.meetingname);
        meetinglink = findViewById(R.id.meetingid);
        meetingdate = findViewById(R.id.meetingdate);
        startTime = findViewById(R.id.meetingStartTime);
        endTime = findViewById(R.id.meetingEndTime);

        mymeetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TrainerMeeting.this, TrainerMyMeetings.class);
                startActivity(i);
            }
        });

        schedulemeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meetingname.setVisibility(View.VISIBLE);
                meetinglink.setVisibility(View.VISIBLE);
                meetingdate.setVisibility(View.VISIBLE);
                startTime.setVisibility(View.VISIBLE);
                endTime.setVisibility(View.VISIBLE);
                addmeeting.setVisibility(View.VISIBLE);
            }
        });

        addmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, link, date, start, end;
                name = meetingname.getEditText().getText().toString().trim();
                link = meetinglink.getEditText().getText().toString().trim();
                date = meetingdate.getEditText().getText().toString().trim();
                start = startTime.getEditText().getText().toString().trim();
                end = endTime.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(name)) {
                    meetingname.setError("Meeting Name is Required !");
                    return;
                }
                if(TextUtils.isEmpty(link)) {
                    meetinglink.setError("Meeting Link is Required !");
                    return;
                }
                if(TextUtils.isEmpty(date)) {
                    meetingdate.setError("Date is Required !");
                    return;
                }
                if(TextUtils.isEmpty(start)) {
                    startTime.setError("Start Time is Required !");
                    return;
                }
                if(TextUtils.isEmpty(end)) {
                    endTime.setError("End Time is Required !");
                    return;
                }

                MeetingDetails md = new MeetingDetails(name, link, date, start, end);

                FirebaseDatabase.getInstance().getReference("Trainers/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Meetings").child(name).setValue(md).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(TrainerMeeting.this, "Meeting scheduled", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(TrainerMeeting.this, TrainerMyMeetings.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(TrainerMeeting.this, "Meeting not scheduled ! Please try again !", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}