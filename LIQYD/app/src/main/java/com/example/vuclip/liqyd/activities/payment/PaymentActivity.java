package com.example.vuclip.liqyd.activities.payment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.activities.BaseActivity;

public class PaymentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_payment_activity);
        configureToolbar(getString(R.string.layout_payment_activity));
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_payment_activity;
    }
}
