package com.example.vuclip.liqyd.ui.payment;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.ui.BaseActivity;

public class PaymentActivity extends BaseActivity {

    CheckBox promoCodeCheckBox;
    EditText promoCodeEditText;
    private ImageView backButton;

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
        backButton = findViewById(R.id.iv_back_button);

        registerCheckboxListener();
        handleBackButtonClick();
    }

    private void handleBackButtonClick() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void registerCheckboxListener() {
        promoCodeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && promoCodeEditText.getVisibility() == View.GONE) {
                    promoCodeEditText.setVisibility(View.VISIBLE);
                } else if (!isChecked && promoCodeEditText.getVisibility() == View.VISIBLE) {
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
