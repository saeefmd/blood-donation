package com.saeefmd.official.miublooddonors.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.saeefmd.official.miublooddonors.R;

public class ProfileActivity extends AppCompatActivity {

    private Spinner bloodGroupsSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bloodGroupsSpinner = findViewById(R.id.profile_spinner_blood_groups);

        setSpinners();

        Button searchBt = findViewById(R.id.profile_search_bt);

        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String requiredBlood = bloodGroupInText(bloodGroupsSpinner.getSelectedItem().toString());


            }
        });
    }

    private String bloodGroupInText(String group) {

        switch (group) {
            case "A+":
                return "A_Positive";
            case "A-":
                return "A_Negative";
            case "B+":
                return "B_Positive";
            case "B-":
                return "B_Negative";
            case "O+":
                return "O_Positive";
            case "O-":
                return "O_Negative";
            case "AB+":
                return "AB_Positive";
            case "AB-":
                return "AB_Negative";
            default:
                return null;
        }
    }

    private void setSpinners() {

        ArrayAdapter<CharSequence> bloodGroupsAdapter = ArrayAdapter.createFromResource(ProfileActivity.this,
                R.array.array_blood_groups, android.R.layout.simple_spinner_item);
        bloodGroupsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroupsSpinner.setAdapter(bloodGroupsAdapter);
    }
}
