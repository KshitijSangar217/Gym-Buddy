package com.example.pbl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.TransitionAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

    RecyclerView recyclerTrainer;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainers_enrolled_member_list);

        //Hooks
        recyclerTrainer = findViewById(R.id.recyclerTrainer);
        recyclerTrainer();
    }

    private void recyclerTrainer() {
        recyclerTrainer.setHasFixedSize(true);
        recyclerTrainer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<TrainersEnrolledHelperClass> enrolledMembersList = new ArrayList<>();
        enrolledMembersList.clear();

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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        /*enrolledMembersList.add(new TrainersEnrolledHelperClass("Kshitij Sangar", R.drawable.ic_baseline_male_50, "Gym", "Paid", "02/04/2021", "02/05/2021"));
        enrolledMembersList.add(new TrainersEnrolledHelperClass("Pratima Patil", R.drawable.ic_baseline_female_50, "Remote", "Paid", "02/04/2021", "02/05/2021"));
        enrolledMembersList.add(new TrainersEnrolledHelperClass("Shailesh Mohite", R.drawable.ic_baseline_transgender_50, "Gym", "Paid", "02/04/2021", "02/05/2021"));
        enrolledMembersList.add(new TrainersEnrolledHelperClass("Jay Kamble", R.drawable.ic_baseline_male_50, "Gym", "Paid", "02/04/2021", "02/05/2021"));*/

    }
}