package com.example.vuclip.liqyd.ui.address;

import android.os.Bundle;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.ui.BaseActivity;

public class AddressActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_address_activity);
        configureToolbar(getString(R.string.address), true);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_address_activity;
    }
}
