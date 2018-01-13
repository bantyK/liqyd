package com.example.vuclip.liqyd.activities.address;

import android.os.Bundle;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.activities.BaseActivity;

public class AddressActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_address_activity);
        configureToolbar(getString(R.string.address));
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_address_activity;
    }
}
