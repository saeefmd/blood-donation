package com.saeefmd.official.miublooddonors.Model;

import java.util.List;

public class BloodGroup {

    String bloodGroup;
    List<DonorList> donorList;

    public BloodGroup(String bloodGroup, List<DonorList> donorList) {
        this.bloodGroup = bloodGroup;
        this.donorList = donorList;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public List<DonorList> getDonorList() {
        return donorList;
    }

    public void setDonorList(List<DonorList> donorList) {
        this.donorList = donorList;
    }

    @Override
    public String toString() {
        return "BloodGroup{" +
                "bloodGroup='" + bloodGroup + '\'' +
                ", donorList=" + donorList +
                '}';
    }
}
