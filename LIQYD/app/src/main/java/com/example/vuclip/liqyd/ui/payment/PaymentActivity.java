package com.example.vuclip.liqyd.ui.payment;

import android.os.Bundle;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.ui.BaseActivity;

public class PaymentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_payment_activity);
        configureToolbar(getString(R.string.layout_payment_activity), true);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_payment_activity;
    }
}
