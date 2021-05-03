package com.example.pbl_android.HelperClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pbl_android.R;

import java.util.ArrayList;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder> {

    ArrayList<MeetingHelperClass> meetingsList;

    public MeetingAdapter(ArrayList<MeetingHelperClass> meetingsList) {
        this.meetingsList = meetingsList;
    }

    @NonNull
    @Override
    public MeetingAdapter.MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meetings_card_design, parent, false);
        MeetingAdapter.MeetingViewHolder meetingViewHolder =  new MeetingAdapter.MeetingViewHolder(view);
        return meetingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingAdapter.MeetingViewHolder holder, int position) {
        MeetingHelperClass meetingHelperClass = meetingsList.get(position);
        holder.meetingname.setText(meetingHelperClass.meetingname);
        holder.meetinglink.setText(meetingHelperClass.link);
        holder.meetingdate.setText(meetingHelperClass.date);
        holder.starttime.setText(meetingHelperClass.startmeetingtime);
        holder.endtime.setText(meetingHelperClass.endmeetingtime);
    }

    @Override
    public int getItemCount() {
        return meetingsList.size();
    }

    public static class MeetingViewHolder extends RecyclerView.ViewHolder{

        TextView meetingname, meetinglink, meetingdate, starttime, endtime;

        public MeetingViewHolder(@NonNull View itemView) {
            super(itemView);

            meetingname = itemView.findViewById(R.id.meetingcardname);
            meetinglink = itemView.findViewById(R.id.meetingcardlink);
            meetingdate = itemView.findViewById(R.id.meetingcarddate);
            starttime = itemView.findViewById(R.id.meetingcardstart);
            endtime = itemView.findViewById(R.id.meetingcardend);
        }
    }
}
