package com.example.vuclip.liqyd.ui.payment;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.ui.BaseActivity;

public class PaymentActivity extends BaseActivity {

    CheckBox promoCodeCheckBox;
    EditText promoCodeEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_payment_activity);
        configureToolbar(getString(R.string.layout_payment_activity), true);
        initUIElements();
    }

    private void initUIElements() {
        promoCodeCheckBox = findViewById(R.id.cb_promo_code);
        promoCodeEditText = findViewById(R.id.et_promo_code);

        promoCodeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked && promoCodeEditText.getVisibility() == View.GONE){
                    promoCodeEditText.setVisibility(View.VISIBLE);
                } else if(!isChecked && promoCodeEditText.getVisibility() == View.VISIBLE) {
                    promoCodeEditText.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_payment_activity;
    }
}
