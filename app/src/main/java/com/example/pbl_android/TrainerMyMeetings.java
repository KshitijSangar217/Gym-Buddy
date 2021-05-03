package com.example.pbl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.pbl_android.HelperClasses.MeetingAdapter;
import com.example.pbl_android.HelperClasses.MeetingHelperClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrainerMyMeetings extends AppCompatActivity {

    RecyclerView recyclermeetings;
    RecyclerView.Adapter meetingsadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_meetings);

        recyclermeetings = findViewById(R.id.recyclerMeetings);
        recyclermeetings();
    }

    private void recyclermeetings() {
        recyclermeetings.setHasFixedSize(true);
        recyclermeetings.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<MeetingHelperClass> meetinglist = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Trainers/" + FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Meetings");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                meetinglist.clear();
                for (DataSnapshot snapsht : snapshot.getChildren())
                {
                    MeetingDetails md = snapsht.getValue(MeetingDetails.class);
                    meetinglist.add(new MeetingHelperClass("Name : " + md.name, "Link : " + md.meetingid, "Date : " + md.date, "Start Time : " + md.startTime, "End Time : " + md.endTime));
                }
                meetingsadapter = new MeetingAdapter(meetinglist);
                recyclermeetings.setAdapter(meetingsadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}