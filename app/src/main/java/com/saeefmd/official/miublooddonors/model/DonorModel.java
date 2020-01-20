package com.saeefmd.official.miublooddonors.model;

public class DonorModel {

    String mobileNumber;
    DonorList donorList;

    public DonorModel(String mobileNumber, DonorList donorList) {
        this.mobileNumber = mobileNumber;
        this.donorList = donorList;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String bloodGroup) {
        this.mobileNumber = bloodGroup;
    }

    public DonorList getDonorList() {
        return donorList;
    }

    public void setDonorList(DonorList donorList) {
        this.donorList = donorList;
    }

    @Override
    public String toString() {
        return "DonorModel{" +
                "bloodGroup='" + mobileNumber + '\'' +
                ", donorList=" + donorList +
                '}';
    }
}
