package com.example.pbl_android.HelperClasses;

public class MeetingHelperClass {
    String meetingname, link, date, startmeetingtime, endmeetingtime;

    public MeetingHelperClass(String meetingname, String link, String date, String startmeetingtime, String endmeetingtime) {
        this.meetingname = meetingname;
        this.link = link;
        this.date = date;
        this.startmeetingtime = startmeetingtime;
        this.endmeetingtime = endmeetingtime;
    }

    public String getMeetingName() {
        return meetingname;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    public String getStartmeetingtime() {
        return startmeetingtime;
    }

    public String getEndmeetingtime() {
        return endmeetingtime;
    }
}
