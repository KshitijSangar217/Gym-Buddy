package com.example.pbl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.TransitionAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pbl_android.HelperClasses.TrainersEnrolledAdapter;
import com.example.pbl_android.HelperClasses.TrainersEnrolledHelperClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class TrainersEnrolledMemberList extends AppCompatActivity {

    Button offlinegymbtn, onlinegymbtn, totalgymbtn;
    RecyclerView recyclerTrainer;
    RecyclerView.Adapter adapter;
    TextView counttxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainers_enrolled_member_list);

        //Hooks
        recyclerTrainer = findViewById(R.id.recyclerTrainer);
        offlinegymbtn = findViewById(R.id.offlinegymbtn);
        onlinegymbtn = findViewById(R.id.onlinegymbtn);
        totalgymbtn = findViewById(R.id.totalgymbtn);
        counttxt = findViewById(R.id.counttext);
        recyclerTrainer();
    }

    private void recyclerTrainer() {
        recyclerTrainer.setHasFixedSize(true);
        recyclerTrainer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<TrainersEnrolledHelperClass> enrolledMembersList = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Trainers/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/gymMembers"); //.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("gymMembers");
        Log.d("Prathmesh", "recyclerTrainer: in " + FirebaseAuth.getInstance().getCurrentUser().getUid() + " " + ref);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                enrolledMembersList.clear();
                for(DataSnapshot snapsht : snapshot.getChildren())
                {
                    MemberintrainerDB mit = snapsht.getValue(MemberintrainerDB.class);
                    String gender = mit.gender;
                    if(gender.equals("M")) {
                        enrolledMembersList.add(new TrainersEnrolledHelperClass(mit.name, R.drawable.ic_baseline_male_50, mit.plan, "Paid", mit.startDate, mit.endDate));
                    }

                    else {
                        enrolledMembersList.add(new TrainersEnrolledHelperClass(mit.name, R.drawable.ic_baseline_female_50, mit.plan, "Paid", mit.startDate, mit.endDate));
                    }
                }
                adapter = new TrainersEnrolledAdapter(enrolledMembersList);
                recyclerTrainer.setAdapter(adapter);
                counttxt.setText(String.valueOf(enrolledMembersList.size()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        totalgymbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerTrainer();
            }
        });


        offlinegymbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerTrainer.setHasFixedSize(true);
                recyclerTrainer.setLayoutManager(new LinearLayoutManager(TrainersEnrolledMemberList.this, LinearLayoutManager.VERTICAL, false));
                ArrayList<TrainersEnrolledHelperClass> offlineenrolledMembersList = new ArrayList<>();

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Trainers/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/gymMembers");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        offlineenrolledMembersList.clear();
                        for (DataSnapshot offlinesnapshot : snapshot.getChildren())
                        {
                            MemberintrainerDB mit = offlinesnapshot.getValue(MemberintrainerDB.class);
                            String plan = mit.plan;
                            if(plan.equals("Offline"))
                            {
                                if(mit.gender.equals("M")) {
                                    offlineenrolledMembersList.add(new TrainersEnrolledHelperClass(mit.name, R.drawable.ic_baseline_male_50, mit.plan, "Paid", mit.startDate, mit.endDate));
                                }

                                else {
                                    offlineenrolledMembersList.add(new TrainersEnrolledHelperClass(mit.name, R.drawable.ic_baseline_female_50, mit.plan, "Paid", mit.startDate, mit.endDate));
                                }
                            }
                        }
                        adapter = new TrainersEnrolledAdapter(offlineenrolledMembersList);
                        recyclerTrainer.setAdapter(adapter);
                        counttxt.setText(String.valueOf(offlineenrolledMembersList.size()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        });


        onlinegymbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerTrainer.setHasFixedSize(true);
                recyclerTrainer.setLayoutManager(new LinearLayoutManager(TrainersEnrolledMemberList.this, LinearLayoutManager.VERTICAL, false));
                ArrayList<TrainersEnrolledHelperClass> onlineenrolledMembersList = new ArrayList<>();

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Trainers/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/gymMembers");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        onlineenrolledMembersList.clear();
                        for (DataSnapshot offlinesnapshot : snapshot.getChildren())
                        {
                            MemberintrainerDB mit = offlinesnapshot.getValue(MemberintrainerDB.class);
                            String plan = mit.plan;
                            if(plan.equals("Online (Remote)"))
                            {
                                if(mit.gender.equals("M")) {
                                    onlineenrolledMembersList.add(new TrainersEnrolledHelperClass(mit.name, R.drawable.ic_baseline_male_50, mit.plan, "Paid", mit.startDate, mit.endDate));
                                }

                                else {
                                    onlineenrolledMembersList.add(new TrainersEnrolledHelperClass(mit.name, R.drawable.ic_baseline_female_50, mit.plan, "Paid", mit.startDate, mit.endDate));
                                }
                            }
                        }
                        adapter = new TrainersEnrolledAdapter(onlineenrolledMembersList);
                        recyclerTrainer.setAdapter(adapter);
                        counttxt.setText(String.valueOf(onlineenrolledMembersList.size()));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        });

        /*enrolledMembersList.add(new TrainersEnrolledHelperClass("Kshitij Sangar", R.drawable.ic_baseline_male_50, "Gym", "Paid", "02/04/2021", "02/05/2021"));
        enrolledMembersList.add(new TrainersEnrolledHelperClass("Pratima Patil", R.drawable.ic_baseline_female_50, "Remote", "Paid", "02/04/2021", "02/05/2021"));
        enrolledMembersList.add(new TrainersEnrolledHelperClass("Shailesh Mohite", R.drawable.ic_baseline_transgender_50, "Gym", "Paid", "02/04/2021", "02/05/2021"));
        enrolledMembersList.add(new TrainersEnrolledHelperClass("Jay Kamble", R.drawable.ic_baseline_male_50, "Gym", "Paid", "02/04/2021", "02/05/2021"));*/

    }




}