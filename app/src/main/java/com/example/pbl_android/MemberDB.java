package com.example.pbl_android;

public class MemberDB {
    public String email, password, name, traineremail, address, plan, gender, bloodgrp, startDate, endDate, contactnum;
    public int age, height, weight;

    public MemberDB() {}

    public MemberDB(String memail, String mpassword, String mname, String tremail, String maddress, String mplan, String mgender, String mbloodgrp, int mage, int mheight, int mweight, String mstartDate, String mendDate, String contactnum)
    {
        email = memail;
        password = mpassword;
        name = mname;
        traineremail = tremail;
        address = maddress;
        plan = mplan;
        gender = mgender;
        bloodgrp = mbloodgrp;
        age = mage;
        height = mheight;
        weight = mweight;
        startDate = mstartDate;
        endDate = mendDate;
        this.contactnum = contactnum;
    }
}
