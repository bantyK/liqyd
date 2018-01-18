package com.example.vuclip.liqyd.ui.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.ui.BaseActivity;
import com.example.vuclip.liqyd.ui.login.login.LoginFragment;
import com.example.vuclip.liqyd.ui.login.registration.RegistrationFragment;

public class LoginRegistrationActivity extends BaseActivity {

    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_registration_activity);
        configureToolbar(getString(R.string.registration), false);
        initUIElements();
        loadFragment(new LoginFragment());

    }

    private void initUIElements() {
        registerButton = findViewById(R.id.btn_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterFragment();
            }
        });
    }

    private void showRegisterFragment() {
        if (getSupportFragmentManager().findFragmentByTag(RegistrationFragment.TAG) == null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new RegistrationFragment(), RegistrationFragment.TAG)
                    .addToBackStack(RegistrationFragment.TAG)
                    .commit();
        registerButton.setVisibility(View.GONE);
    }


    private void loadFragment(Fragment fragmentToAdd) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragmentToAdd, LoginFragment.TAG)
                .commit();

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_login_registration_activity;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(registerButton != null)
            registerButton.setVisibility(View.VISIBLE);
    }
}
