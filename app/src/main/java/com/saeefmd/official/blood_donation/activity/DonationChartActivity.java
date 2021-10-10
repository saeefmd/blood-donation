package com.saeefmd.official.blood_donation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.saeefmd.official.blood_donation.R;
import com.saeefmd.official.blood_donation.adapter.DonationChartAdapter;
import com.saeefmd.official.blood_donation.model.DonationChartModel;

import java.util.ArrayList;
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

        donationChartModelList = new ArrayList<>();

        donationChartModelList.add(new DonationChartModel("A+", "A+, A-", "A-"));
        donationChartModelList.add(new DonationChartModel("A-", "A-", "A+, A-"));
        donationChartModelList.add(new DonationChartModel("B+", "B+, B-", "B-"));
        donationChartModelList.add(new DonationChartModel("B-", "B-", "B+, B-"));
        donationChartModelList.add(new DonationChartModel("AB+", "AB+, AB-", "AB-"));
        donationChartModelList.add(new DonationChartModel("AB-", "AB-", "AB+, AB-"));
        donationChartModelList.add(new DonationChartModel("O+", "All Positive", "O+"));
        donationChartModelList.add(new DonationChartModel("O-", "O-", "O-"));
    }

    private void setRecyclerView() {

        donationChartRv.setLayoutManager(new LinearLayoutManager(DonationChartActivity.this));

        DonationChartAdapter adapter = new DonationChartAdapter(DonationChartActivity.this, donationChartModelList);
        donationChartRv.setAdapter(adapter);
    }
}