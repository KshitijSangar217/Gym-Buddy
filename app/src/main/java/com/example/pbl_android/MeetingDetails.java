package com.example.pbl_android;

public class MeetingDetails {
    public String name, meetingid, date, startTime, endTime;

    MeetingDetails() { }

    MeetingDetails (String name, String meetingid, String date, String startTime, String endTime)
    {
        this.name = name;
        this.meetingid = meetingid;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
