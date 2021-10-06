package com.saeefmd.official.blood_donation.model;

public class UserInfo {

    private String name;
    private String location;
    private String mobile;
    private String bloodGroup;
    private String age;
    private String gender;
    private String lastDonateDate;

    public UserInfo(String name, String location, String mobile, String bloodGroup, String age, String gender, String lastDonateDate) {

        this.name = name;
        this.location = location;
        this.mobile = mobile;
        this.bloodGroup = bloodGroup;
        this.age = age;
        this.gender = gender;
        this.lastDonateDate = lastDonateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastDonateDate() {
        return lastDonateDate;
    }

    public void setLastDonateDate(String lastDonateDate) {
        this.lastDonateDate = lastDonateDate;
    }
}
