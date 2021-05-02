package com.example.pbl_android.HelperClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pbl_android.R;

import java.util.ArrayList;
import java.util.Date;

public class TrainersEnrolledAdapter extends RecyclerView.Adapter<TrainersEnrolledAdapter.TrainerViewHolder> {

    ArrayList<TrainersEnrolledHelperClass> enrolledMembersList;

    public TrainersEnrolledAdapter(ArrayList<TrainersEnrolledHelperClass> enrolledMembersList) {
        this.enrolledMembersList = enrolledMembersList;
    }

    @NonNull
    @Override
    public TrainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainers_enrolled_card_design, parent, false);
        TrainerViewHolder trainerViewHolder =  new TrainerViewHolder(view);
        return trainerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrainerViewHolder holder, int position) {
        TrainersEnrolledHelperClass trainersEnrolledHelperClass = enrolledMembersList.get(position);
        holder.name.setText(trainersEnrolledHelperClass.getName());
        holder.gender.setImageResource(trainersEnrolledHelperClass.getGender());
        holder.mode.setText(trainersEnrolledHelperClass.getMode());
        holder.fee.setText(trainersEnrolledHelperClass.getFee());
        holder.startdate.setText(trainersEnrolledHelperClass.getName());
        holder.enddate.setText(trainersEnrolledHelperClass.getName());
    }

    @Override
    public int getItemCount() {
        return enrolledMembersList.size();
    }

    public static class TrainerViewHolder extends RecyclerView.ViewHolder{

        Button name, mode, fee, startdate, enddate;
        AppCompatImageButton gender;
        public TrainerViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.trainerCardName);
            gender = itemView.findViewById(R.id.trainerCardGender);
            mode = itemView.findViewById(R.id.trainerCardMode);
            fee = itemView.findViewById(R.id.trainerCardFee);
            startdate = itemView.findViewById(R.id.trainerCardStartDt);
            enddate = itemView.findViewById(R.id.trainerCardEndDt);
        }
    }
}
