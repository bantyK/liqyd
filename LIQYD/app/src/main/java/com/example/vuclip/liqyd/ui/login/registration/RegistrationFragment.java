package com.example.vuclip.liqyd.ui.login.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.ui.gallary.GallaryActivity;

/**
 * Created by Banty on 13/01/18.
 */

public class RegistrationFragment extends Fragment implements IRegisterView {
    public static final String TAG = "RegistrationFragment";

    private EditText nameEditText, mobileEditText, emailEditText, passwordEditText;
    private CheckBox termsAndConditions;
    private Button registerButton;
    private RegisterPresenter mRegisterPresenter;

    public RegistrationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_registration_fragment, container, false);
        initUIElements(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mRegisterPresenter = new RegisterPresenter(this);
    }

    private void initUIElements(View view) {
        nameEditText = view.findViewById(R.id.et_name);
        emailEditText = view.findViewById(R.id.et_email_id);
        mobileEditText = view.findViewById(R.id.et_mobile);
        passwordEditText = view.findViewById(R.id.et_password);
        registerButton = view.findViewById(R.id.btn_register);
        termsAndConditions = view.findViewById(R.id.cb_terms_conditions);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String name = nameEditText.getText().toString();
        String mobile = mobileEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (mRegisterPresenter.validEntries(name, mobile, email, password)) {
            if (termsAndConditions.isChecked()) {
                mRegisterPresenter.registerUser(name, mobile, email, password);
                mRegisterPresenter.onRegistrationSuccess();
            } else
                Toast.makeText(getContext(), "Please accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Please fill in all the details", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showGallery() {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), GallaryActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
