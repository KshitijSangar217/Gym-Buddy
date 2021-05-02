package com.example.pbl_android.HelperClasses;

public class TrainersEnrolledHelperClass {
    String name, mode, fee;
    String start, end;
    int gender;

    public TrainersEnrolledHelperClass(String name, int gender, String mode, String fee, String start, String end) {
        this.name = name;
        this.gender = gender;
        this.mode = mode;
        this.fee = fee;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public String getMode() {
        return mode;
    }

    public String getFee() {
        return fee;
    }

    public String getStart() {
        return start;
    }

    public String  getEnd() {
        return end;
    }
}
