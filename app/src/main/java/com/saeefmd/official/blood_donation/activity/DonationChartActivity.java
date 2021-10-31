package com.saeefmd.official.blood_donation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.saeefmd.official.blood_donation.R;
import com.saeefmd.official.blood_donation.adapter.DonationChartAdapter;
import com.saeefmd.official.blood_donation.model.DonationChartModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DonationChartActivity extends AppCompatActivity {

    private List<DonationChartModel> donationChartModelList;

    private RecyclerView donationChartRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_chart);

        donationChartRv = findViewById(R.id.donation_chart_rv);

        loadChartData();

        setRecyclerView();
    }

    private void loadChartData() {

        donationChartModelList = new ArrayList<>(Arrays.asList(
                new DonationChartModel("A+", "AB+, A+", "O+, O-, A+, A-"),
                new DonationChartModel("A-", "AB+, AB-, A+, A-", "O-, A-"),
                new DonationChartModel("B+", "AB+, B+", "O+, O-, B+, B-"),
                new DonationChartModel("B-", "AB+, AB-, B+, B-", "O-, B-"),
                new DonationChartModel("AB+", "AB+", "All Blood Group"),
                new DonationChartModel("AB-", "AB+, AB-", "All Positive"),
                new DonationChartModel("O+", "All Positive", "O+, O-"),
                new DonationChartModel("O-", "All Blood Group", "O-")
        ));

        /*donationChartModelList.add(new DonationChartModel("A+", "AB+, A+", "O+, O-, A+, A-"));
        donationChartModelList.add(new DonationChartModel("A-", "AB+, AB-, A+, A-", "O-, A-"));
        donationChartModelList.add(new DonationChartModel("B+", "AB+, B+", "O+, O-, B+, B-"));
        donationChartModelList.add(new DonationChartModel("B-", "AB+, AB-, B+, B-", "O-, B-"));
        donationChartModelList.add(new DonationChartModel("AB+", "AB+", "All Blood Group"));
        donationChartModelList.add(new DonationChartModel("AB-", "AB+, AB-", "All Positive"));
        donationChartModelList.add(new DonationChartModel("O+", "All Positive", "O+, O-"));
        donationChartModelList.add(new DonationChartModel("O-", "All Blood Group", "O-"));*/
    }

    private void setRecyclerView() {

        donationChartRv.setLayoutManager(new LinearLayoutManager(DonationChartActivity.this));

        DonationChartAdapter adapter = new DonationChartAdapter(DonationChartActivity.this, donationChartModelList);
        donationChartRv.setAdapter(adapter);
    }
}