package com.saeefmd.official.blood_donation.model;

public class DonorModel {

    String mobileNumber;
    DonorData donorData;

    public DonorModel(String mobileNumber, DonorData donorData) {
        this.mobileNumber = mobileNumber;
        this.donorData = donorData;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String bloodGroup) {
        this.mobileNumber = bloodGroup;
    }

    public DonorData getDonorData() {
        return donorData;
    }

    public void setDonorData(DonorData donorData) {
        this.donorData = donorData;
    }

    @Override
    public String toString() {
        return "DonorModel{" +
                "bloodGroup='" + mobileNumber + '\'' +
                ", donorList=" + donorData +
                '}';
    }
}
