package com.saeefmd.official.blood_donation.utilities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.saeefmd.official.blood_donation.activity.ProfileActivity;
import com.saeefmd.official.blood_donation.data.CurrentUser;
import com.saeefmd.official.blood_donation.data.Variables;
import com.saeefmd.official.blood_donation.model.UserInfo;
import com.saeefmd.official.blood_donation.R;

import static android.content.Context.MODE_PRIVATE;

public class ConfirmUserInfoDialog extends Dialog {

    private static final String TAG = "ConfirmUserInfoDialog";

    private Context context;

    private String userName;
    private String userLocation;
    private String userBloodGroup;
    private String userMobile;
    private String userGender;
    private String userAge;
    private String lastDonateDate;
    private String userEmail;

    private TextView userNameTv;
    private TextView userLocationTv;
    private TextView userBloodGroupTv;
    private TextView userMobileTv;
    private TextView userGenderTv;
    private TextView userAgeTv;
    private TextView lastDonateDateTv;

    private DatabaseReference firebaseReference;
    private FirebaseDatabase firebaseDatabase;

    WaitAlertDialog mWaitAlertDialog;

    public ConfirmUserInfoDialog(Context context,String userName, String userLocation, String userBloodGroup, String userMobile,
                                 String userGender, String userAge, String lastDonateDate) {
        super(context);
        this.context = context;
        this.userName = userName;
        this.userLocation = userLocation;
        this.userBloodGroup = userBloodGroup;
        this.userMobile = userMobile;
        this.userAge = userAge;
        this.userGender = userGender;
        this.lastDonateDate = lastDonateDate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_user_info);

        userNameTv = findViewById(R.id.my_info_name_tv);
        userLocationTv = findViewById(R.id.my_info_location_tv);
        userBloodGroupTv = findViewById(R.id.my_info_blood_group_tv);
        userMobileTv = findViewById(R.id.my_info_mobile_tv);
        userAgeTv = findViewById(R.id.my_info_age_tv);
        userGenderTv = findViewById(R.id.my_info_gender_tv);
        lastDonateDateTv = findViewById(R.id.my_info_last_donate_tv);

        mWaitAlertDialog = new WaitAlertDialog(context);

        firebaseDatabase = FirebaseDatabase.getInstance();

        setUserData();

        Button cancelBt = findViewById(R.id.my_info_cancel_bt);
        Button saveBt = findViewById(R.id.my_info_ok_bt);

        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bloodGroupText = bloodGroupInText(userBloodGroup);
                firebaseReference = firebaseDatabase.getReference(bloodGroupText);

                mWaitAlertDialog.show();

                saveCurrentUser();
                inputUser();
                dismiss();
            }
        });
    }

    private void setUserData() {

        userEmail = CurrentUser.getUserEmail(context);

        userNameTv.setText(userName);
        userLocationTv.setText("Location: " + userLocation);
        userBloodGroupTv.setText("Blood Group: " + userBloodGroup);
        userMobileTv.setText("Mobile: " + userMobile);
        userGenderTv.setText("Gender: " + userGender);
        userAgeTv.setText("Age: " + userAge);
        lastDonateDateTv.setText("Last Donate: " + lastDonateDate);
    }

    private void inputUser() {

        UserInfo userInfo = new UserInfo(userName, userLocation, userMobile, userBloodGroup, userAge, userGender, lastDonateDate);

        try {
            firebaseReference.child(userMobile).setValue(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {

                        mWaitAlertDialog.dismiss();
                        Toast.makeText(context, "Your Information Stored Successfully. Thank You", Toast.LENGTH_SHORT).show();

                        //subscribeToFcm(userBloodGroup);

                        SharedPreferences.Editor editor = context.getSharedPreferences(Variables.SHARED_PREFERENCE_DB, MODE_PRIVATE).edit();
                        editor.putBoolean(Variables.FIRST_TIME_FLAG, false);
                        editor.apply();

                        Intent intent = new Intent(context, ProfileActivity.class);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                    } else {
                        mWaitAlertDialog.dismiss();
                        Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    private void saveCurrentUser() {
        CurrentUser.setCurrentUser(context, userName, userLocation, userBloodGroup, userMobile, userAge, userGender,
                userEmail, lastDonateDate);
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

    private void subscribeToFcm(String bloodGroup) {

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                    return;
                }

                // Get new FCM registration token
                String token = task.getResult();

                FirebaseMessaging.getInstance().subscribeToTopic(bloodGroup)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                String msg = "Subscription Successful";
                                if (!task.isSuccessful()) {
                                    msg = "Subscription Failed";
                                }
                                Log.d(TAG, msg);
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                            }
                        });

                Log.d("FCM Token: ", token);
            }
        });
    }
}
