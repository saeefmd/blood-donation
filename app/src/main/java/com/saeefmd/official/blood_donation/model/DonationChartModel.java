package com.saeefmd.official.blood_donation.model;

public class DonationChartModel {
    private String bloodGroup;
    private String canDonateTo;
    private String canReceiveFrom;

    public DonationChartModel(String bloodGroup, String canDonateTo, String canReceiveFrom) {
        this.bloodGroup = bloodGroup;
        this.canDonateTo = canDonateTo;
        this.canReceiveFrom = canReceiveFrom;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getCanDonateTo() {
        return canDonateTo;
    }

    public void setCanDonateTo(String canDonateTo) {
        this.canDonateTo = canDonateTo;
    }

    public String getCanReceiveFrom() {
        return canReceiveFrom;
    }

    public void setCanReceiveFrom(String canReceiveFrom) {
        this.canReceiveFrom = canReceiveFrom;
    }
}
