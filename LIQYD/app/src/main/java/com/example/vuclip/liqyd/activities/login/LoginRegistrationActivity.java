package com.example.vuclip.liqyd.activities.login;

import android.os.Bundle;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.activities.BaseActivity;

public class LoginRegistrationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_registration_activity);
        configureToolbar("REGISTRATION");

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_login_registration_activity;
    }
}
