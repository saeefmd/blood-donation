package com.saeefmd.official.miublooddonors.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.saeefmd.official.miublooddonors.R;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordResetFragment extends Fragment {

    private EditText resetMailEt;

    public PasswordResetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_password_reset, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        resetMailEt = view.findViewById(R.id.reset_email_et);

        Button resetBt = view.findViewById(R.id.reset_bt);

        resetBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = resetMailEt.getText().toString();

                resetPassword(email);
            }
        });
    }

    private void resetPassword(String email) {

        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "A password reset link has been sent" +
                                    " to your email", Toast.LENGTH_SHORT).show();

                            switchToLogInFragment();
                        } else {
                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT);
                        }
                    }
                });
    }

    private void switchToLogInFragment() {

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity_fragment_container, new LogInFragment()).commit();
    }
}
