package com.example.vuclip.liqyd.ui.address;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.helper.SharedPrefHelper;
import com.example.vuclip.liqyd.ui.BaseActivity;
import com.example.vuclip.liqyd.user.UserConstants;
import com.example.vuclip.liqyd.user.UserManager;

public class AddressActivity extends BaseActivity {

    private static final String TAG = "AddressActivity";
    private EditText address1, address2, landmark;
    private Button saveAddressButton;
    private CheckBox defaultAddressCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_address_activity);
        configureToolbar(getString(R.string.address), true);
        initUIElements();
        checkAndPrePopulateAddress();
    }

    private void checkAndPrePopulateAddress() {
        if (addressSaved()) {
            address1.setText(SharedPrefHelper.getPref(UserConstants.USER_ADDRESS_1, ""));
            address2.setText(SharedPrefHelper.getPref(UserConstants.USER_ADDRESS_2, ""));
            landmark.setText(SharedPrefHelper.getPref(UserConstants.USER_ADDRESS_LANDMARK, ""));
        }
    }

    private boolean addressSaved() {
        return !(TextUtils.isEmpty(SharedPrefHelper.getPref(UserConstants.USER_ADDRESS_1, "")) &&
                TextUtils.isEmpty(SharedPrefHelper.getPref(UserConstants.USER_ADDRESS_2, "")) &&
                TextUtils.isEmpty(SharedPrefHelper.getPref(UserConstants.USER_ADDRESS_LANDMARK, ""))
        );
    }

    private void initUIElements() {
        ImageView backButton = findViewById(R.id.iv_back_button);
        address1 = findViewById(R.id.et_address_line_1);
        address2 = findViewById(R.id.et_address_line_2);
        landmark = findViewById(R.id.et_landmark);
        saveAddressButton = findViewById(R.id.save_button);
        defaultAddressCheckbox = findViewById(R.id.cb_default_address_checkbox);
        registerClickListener(backButton);
        registerClickListener(saveAddressButton);

    }


    private void registerClickListener(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_back_button:
                        finish();
                        break;

                    case R.id.save_button:
                        if (defaultAddressCheckbox.isChecked()) {
                            UserManager.getInstance().saveUserAddress(
                                    address1.getText().toString(),
                                    address2.getText().toString(),
                                    landmark.getText().toString());
                        } else {
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra(UserConstants.USER_ADDRESS_1, address1.getText().toString());
                            resultIntent.putExtra(UserConstants.USER_ADDRESS_2, address2.getText().toString());
                            resultIntent.putExtra(UserConstants.USER_ADDRESS_LANDMARK, landmark.getText().toString());
                            setResult(Activity.RESULT_OK, resultIntent);
                        }
                        finish();
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_address_activity;
    }
}
