package com.example.vuclip.liqyd.ui.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.helper.SharedPrefHelper;
import com.example.vuclip.liqyd.ui.BaseActivity;
import com.example.vuclip.liqyd.ui.address.AddressActivity;
import com.example.vuclip.liqyd.user.UserConstants;
import com.example.vuclip.liqyd.user.UserManager;

public class PaymentActivity extends BaseActivity {

    private CheckBox promoCodeCheckBox;
    private EditText promoCodeEditText;
    private ImageView backButton;
    private TextView changeAddressButton;
    private EditText addressEditText;

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
        changeAddressButton = findViewById(R.id.change_address);
        addressEditText = findViewById(R.id.et_delivery_address);

        registerCheckboxListener();
        registerClickListener(backButton);
        registerClickListener(changeAddressButton);

        setUpDefaultAddress(addressEditText);
    }

    private void setUpDefaultAddress(EditText addressEditText) {
        if (UserManager.getInstance().defaultAddressSet()) {
            addressEditText.setText(getUserAddress());
        } else {
            addressEditText.setText("");
        }
    }

    @NonNull
    private String getUserAddress() {
        return SharedPrefHelper.getPref(UserConstants.USER_ADDRESS_1, "") + "," +
                SharedPrefHelper.getPref(UserConstants.USER_ADDRESS_2, null) + "\n" +
                SharedPrefHelper.getPref(UserConstants.USER_LANDMARK, null) + "," +
                getString(R.string.pune) + " " + getString(R.string.maharashtra);
    }

    private void registerClickListener(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_back_button:
                        finish();
                        break;
                    case R.id.change_address:
                        launchAddressActivity();
                        break;
                }
            }
        });
    }

    private void launchAddressActivity() {
        Intent intent = new Intent(this, AddressActivity.class);
        startActivity(intent);
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
