package com.saeefmd.official.miublooddonors.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.saeefmd.official.miublooddonors.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends Fragment {

    private Spinner departmentsSpinner;
    private Spinner bloodGroupsSpinner;
    private Spinner locationsSpinner;

    private String userName;
    private String userLocation;
    private String userBloodGroup;
    private String userDpartment;
    private String userBatch;
    private String userStudentId;
    private String userMobile;

    private EditText firstNameEt;
    private EditText lastNameEt;
    private EditText batchEt;
    private EditText studentIdEt;
    private EditText mobileEt;

    private Button continueBt;

    public UserInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        departmentsSpinner = view.findViewById(R.id.spinner_departments);
        bloodGroupsSpinner = view.findViewById(R.id.spinner_blood_groups);
        locationsSpinner = view.findViewById(R.id.spinner_locations);

        firstNameEt = view.findViewById(R.id.first_name_et);
        lastNameEt = view.findViewById(R.id.last_name_et);
        batchEt = view.findViewById(R.id.batch_no_et);
        studentIdEt = view.findViewById(R.id.student_id_et);

        setSpinners();
    }

    private void setSpinners() {

        ArrayAdapter<CharSequence> departmentsAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.array_departments, android.R.layout.simple_spinner_item);
        departmentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentsSpinner.setAdapter(departmentsAdapter);


        ArrayAdapter<CharSequence> bloodGroupsAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.array_blood_groups, android.R.layout.simple_spinner_item);
        bloodGroupsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroupsSpinner.setAdapter(bloodGroupsAdapter);


        ArrayAdapter<CharSequence> locationsAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.array_departments, android.R.layout.simple_spinner_item);
        locationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationsSpinner.setAdapter(locationsAdapter);
    }
}
