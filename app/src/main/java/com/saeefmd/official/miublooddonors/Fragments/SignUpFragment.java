package com.saeefmd.official.miublooddonors.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saeefmd.official.miublooddonors.FirebaseUtilities.EmailAuthentication;
import com.saeefmd.official.miublooddonors.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    private EditText signupEmailEt;
    private EditText signupPasswordEt;
    private EditText signupConfirmPasswordEt;

    private String email;
    private String password;
    private String confirmPassword;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signupEmailEt = view.findViewById(R.id.signup_email_et);
        signupPasswordEt = view.findViewById(R.id.signup_password_et);
        signupConfirmPasswordEt = view.findViewById(R.id.signup_confirm_password_et);
        Button signUpBt = view.findViewById(R.id.sign_up_bt);

        signUpBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = signupEmailEt.getText().toString().trim();
                password = signupPasswordEt.getText().toString().trim();
                confirmPassword = signupConfirmPasswordEt.getText().toString().trim();

                boolean flag = checkTextFields();

                if (flag) {

                    EmailAuthentication emailAuthentication = new EmailAuthentication(email, password, getContext());
                    emailAuthentication.createAccount();

                    switchToLogInFragment();
                } else {

                    Toast.makeText(getContext(), "Please, Provide Info", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void switchToLogInFragment() {

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity_fragment_container, new LogInFragment()).addToBackStack(null).commit();
    }

    private boolean checkTextFields() {

        if (email.isEmpty()) {
            signupEmailEt.setError("Empty");
        }

        if (password.isEmpty()) {
            signupPasswordEt.setError("Empty");
        }

        if (confirmPassword.isEmpty()) {
            signupConfirmPasswordEt.setError("Empty");
        }

        if (!password.equals(confirmPassword)) {
            signupConfirmPasswordEt.setError("Not Matched");
        }

        if (!email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
            if (password.equals(confirmPassword)) {
                return true;
            }
        }

        return false;
    }
}
