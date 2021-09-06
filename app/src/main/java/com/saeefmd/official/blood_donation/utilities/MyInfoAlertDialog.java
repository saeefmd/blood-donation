package com.saeefmd.official.blood_donation.utilities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.saeefmd.official.blood_donation.data.CurrentUser;
import com.saeefmd.official.blood_donation.R;

public class MyInfoAlertDialog extends Dialog{

    private Context context;

    private TextView userNameTv;
    private TextView userLocationTv;
    private TextView userBloodGroupTv;
    private TextView userDepartmentTv;
    private TextView userMobileTv;

    public MyInfoAlertDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_my_info);

        userNameTv = findViewById(R.id.my_info_name_tv);
        userLocationTv = findViewById(R.id.my_info_location_tv);
        userBloodGroupTv = findViewById(R.id.my_info_blood_group_tv);
        userDepartmentTv = findViewById(R.id.my_info_department_tv);
        userMobileTv = findViewById(R.id.my_info_mobile_tv);

        setUserData();

        Button okBt = findViewById(R.id.my_info_ok_bt);

        okBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void setUserData() {

        CurrentUser currentUser = new CurrentUser(context);
        currentUser.getCurrentUser();

        String userName = currentUser.getUserName();
        String userLocation = currentUser.getUserLocation();
        String userBloodGroup = currentUser.getUserBloodGroup();
        String userDepartment = currentUser.getUserDepartment();
        String userMobile = currentUser.getUserMobile();

        if (!userName.equals("Empty")) {
            userNameTv.setText(userName);
            userLocationTv.setText("Location: " + userLocation);
            userBloodGroupTv.setText("Blood Group: " + userBloodGroup);
            userDepartmentTv.setText("Department of " + userDepartment);
            userMobileTv.setText("Mobile: " + userMobile);
        } else {
            userNameTv.setText("No Data");
        }

    }

}
