package com.example.pbl_android;

public class MemberintrainerDB {
    public String phonenum, name, gender, startDate, endDate, plan;
    boolean feestatus=false;

    MemberintrainerDB() {}

    MemberintrainerDB(String phonenum, String name, String gender, String startDate, String endDate, String plan, boolean feestatus) {
        this.phonenum = phonenum;
        this.name = name;
        this.gender = gender;
        this.startDate = startDate;
        this.endDate = endDate;
        this.plan = plan;
        this.feestatus = feestatus;
    }
}
