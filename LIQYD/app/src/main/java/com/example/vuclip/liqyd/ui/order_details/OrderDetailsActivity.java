package com.example.vuclip.liqyd.ui.order_details;

import android.os.Bundle;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.ui.BaseActivity;

public class OrderDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_order_details_activity);
        configureToolbar(getString(R.string.order_details), true);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_order_details_activity;
    }
}
