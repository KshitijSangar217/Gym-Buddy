package com.example.pbl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.pbl_android.HelperClasses.MeetingAdapter;
import com.example.pbl_android.HelperClasses.MeetingHelperClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MemberMyMeetings extends AppCompatActivity {

    RecyclerView recyclermeetings;
    RecyclerView.Adapter meetingsadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_my_meetings);

        recyclermeetings = findViewById(R.id.recyclerMeetings);

        DatabaseReference r = FirebaseDatabase.getInstance().getReference("Members/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/traineremail");
        r.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String traineremail = snapshot.getValue(String.class);
                Log.d("Prathmesh", "onDataChange: " + traineremail);

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Trainers");
                Query checkuser = ref.orderByChild("email").equalTo(traineremail);

                checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            for (DataSnapshot snapsht : snapshot.getChildren())
                            {
                                String key = snapsht.getKey();
                                recyclermeetings(key);
                                Log.d("Prathmesh", "onDataChange: " + key);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });


    }

    private void recyclermeetings(String key) {
        recyclermeetings.setHasFixedSize(true);
        recyclermeetings.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<MeetingHelperClass> meetinglist = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Trainers/" + key +"/Meetings");

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