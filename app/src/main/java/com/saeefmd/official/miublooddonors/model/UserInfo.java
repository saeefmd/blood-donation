package com.saeefmd.official.miublooddonors.model;

public class UserInfo {

    private String name;
    private String department;
    private String studentId;
    private String batch;
    private String location;
    private String mobile;
    private String bloodGroup;

    public UserInfo(String name, String department, String studentId, String batch,
                    String location, String mobile, String bloodGroup) {

        this.name = name;
        this.department = department;
        this.studentId = studentId;
        this.batch = batch;
        this.location = location;
        this.mobile = mobile;
        this.bloodGroup = bloodGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}
