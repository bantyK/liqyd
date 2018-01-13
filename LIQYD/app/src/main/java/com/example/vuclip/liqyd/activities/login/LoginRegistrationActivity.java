package com.example.vuclip.liqyd.activities.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.activities.BaseActivity;

public class LoginRegistrationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_registration_activity);
        configureToolbar(getString(R.string.registration));

        loadFragment(new LoginFragment());

    }

    private void loadFragment(Fragment fragmentToAdd) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragmentToAdd)
                .commit();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_login_registration_activity;
    }

}
