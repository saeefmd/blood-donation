package com.saeefmd.official.miublooddonors.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.saeefmd.official.miublooddonors.FirebaseUtilities.EmailAuthentication;
import com.saeefmd.official.miublooddonors.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LogInFragment extends Fragment {

    private EditText loginEmailEt;
    private EditText loginPasswordEt;

    private String userMail;
    private String userPassword;

    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginEmailEt = view.findViewById(R.id.login_email_et);
        loginPasswordEt = view.findViewById(R.id.login_password_et);
        Button loginBt = view.findViewById(R.id.log_in_bt);
        TextView loginSignUpTv = view.findViewById(R.id.login_sign_up_tv);

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userMail = loginEmailEt.getText().toString().trim();
                userPassword = loginPasswordEt.getText().toString().trim();

                EmailAuthentication emailAuthentication = new EmailAuthentication(userMail, userPassword, getContext());
                emailAuthentication.signIn();
            }
        });

        loginSignUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchToSignUpFragment();
            }
        });
    }

    private void switchToSignUpFragment() {

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity_fragment_container, new SignUpFragment()).addToBackStack(null).commit();
    }

}
