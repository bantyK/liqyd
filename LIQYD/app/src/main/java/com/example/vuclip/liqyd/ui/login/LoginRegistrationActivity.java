package com.example.vuclip.liqyd.ui.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.ui.BaseActivity;
import com.example.vuclip.liqyd.ui.login.login.LoginFragment;

public class LoginRegistrationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_registration_activity);
        configureToolbar(getString(R.string.registration), false);

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
