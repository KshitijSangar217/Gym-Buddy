package com.example.pbl_android;


public class TrainerDB {
    //Made for demo purpose (DO NOT DELETE)
    public static class gymMembers{ //Static means -> We can call the functions of this class, without creating an object of it.
      String gm_id, gm_email;
      public gymMembers(String gm_id, String gm_email){
          this.gm_id = gm_id;
          this.gm_email = gm_email;
      }


    }

    //Main TrainerDB Class being declared
    public String tid, email, name, address, phone, gender, gpayid, certification, description, onlinefee, offlinefee;
    public boolean online, offline;
    public TrainerDB(String email, String name, String address, String phone, String gender, String gpayid, String certification, String description, String onlinefee, String offlinefee, boolean online, boolean offline){
        this.email = email;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.gpayid = gpayid;
        this.certification = certification;
        this.description = description;
        this.onlinefee = onlinefee;
        this.offlinefee = offlinefee;
        this.online = online;
        this.offline = offline;
    }
}
