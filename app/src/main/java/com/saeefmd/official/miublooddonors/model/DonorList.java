package com.saeefmd.official.miublooddonors.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonorList {

    @SerializedName("batch")
    @Expose
    private String batch;
    @SerializedName("department")
    @Expose
    private String department;
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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    @Override
    public String toString() {
        return "DonorList{" +
                "batch='" + batch + '\'' +
                ", department='" + department + '\'' +
                ", location='" + location + '\'' +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
