package com.example.pbl_android;


public class TrainerDB {
    //List of all the Gym members enrolled to the Trainer
    public static class gymMembers{
      //gm -> gym member
      String gm_id, gm_email;
      public gymMembers(String gm_id, String gm_email){
          this.gm_id = gm_id;
          this.gm_email = gm_email;
      }
    }
    //List of all the Gym members enrolled to the Trainer
    public static class remoteMembers{
        //rm -> remote member
        String rm_id, rm_email;
        public remoteMembers(String rm_id, String rm_email){
            this.rm_id = rm_id;
            this.rm_email = rm_email;
        }
    }

    //Main TrainerDB Class being declared
    public String tid, email, name, address, phone, gender;
    gymMembers gm = new gymMembers(null, null);
    remoteMembers rm = new remoteMembers(null, null);

    public TrainerDB() {}

    public TrainerDB(String email, String name, String address, String phone, String gender, gymMembers gm, remoteMembers rm){
        this.email = email;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.gm = gm;
        this.rm = rm;
    }
}
