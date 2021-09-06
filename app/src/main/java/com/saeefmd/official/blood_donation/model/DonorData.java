package com.saeefmd.official.blood_donation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonorData {

    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("studentId")
    @Expose
    private String studentId;
    @SerializedName("bloodGroup")
    @Expose
    private String bloodGroup;
    @SerializedName("lastDonateDate")
    @Expose
    private long lastDonateDate;

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public long getLastDonateDate() {
        return lastDonateDate;
    }

    public void setLastDonateDate(long lastDonateDate) {
        this.lastDonateDate = lastDonateDate;
    }

    @Override
    public String toString() {
        return "DonorList{" +
                ", location='" + location + '\'' +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", studentId='" + studentId + '\'' +
                ", lastDonateDate='" + lastDonateDate +
                '}';
    }
}
