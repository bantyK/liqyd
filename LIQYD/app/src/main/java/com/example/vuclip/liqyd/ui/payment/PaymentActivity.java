package com.example.vuclip.liqyd.ui.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.helper.IntentExtras;
import com.example.vuclip.liqyd.helper.SharedPrefHelper;
import com.example.vuclip.liqyd.models.Product;
import com.example.vuclip.liqyd.ui.BaseActivity;
import com.example.vuclip.liqyd.ui.address.AddressActivity;
import com.example.vuclip.liqyd.user.UserConstants;
import com.example.vuclip.liqyd.user.UserManager;

public class PaymentActivity extends BaseActivity {

    private static final String TAG = "PaymentActivity";

    private CheckBox promoCodeCheckBox;
    private EditText promoCodeEditText;
    private ImageView backButton;
    private TextView changeAddressTextView, productNameTextView, quantityTextView, priceSingleItemTextView;
    private TextView orderTotalAmountTextView, amountPayableTextView, totalAmountTextView;
    private EditText addressEditText;
    private ImageView productImage;

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
        changeAddressTextView = findViewById(R.id.change_address);
        addressEditText = findViewById(R.id.et_delivery_address);
        priceSingleItemTextView = findViewById(R.id.price_one_item);
        orderTotalAmountTextView = findViewById(R.id.order_total_amount);
        amountPayableTextView = findViewById(R.id.amount_payable);
        totalAmountTextView = findViewById(R.id.total_amount_payable);

        productImage = findViewById(R.id.product_image);
        productNameTextView = findViewById(R.id.product_name);
        quantityTextView = findViewById(R.id.quantity);

        registerCheckboxListener();
        registerClickListener(backButton);
        registerClickListener(changeAddressTextView);

        setUpUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpDefaultAddress(addressEditText);
    }

    private void setUpUI() {
        Product product = getProductFromIntent();
        if (product != null) {
            productNameTextView.setText(product.getName());
            productImage.setImageResource(product.getDrawableImage());
            int quantityFromIntent = getQuantityFromIntent();
            quantityTextView.setText(String.valueOf(quantityFromIntent));
            String singleItemPrice = product.getPrice();
            priceSingleItemTextView.setText(String.format("%s %s", getString(R.string.rupees), String.valueOf(singleItemPrice)));
            orderTotalAmountTextView.setText(getTotalAmountPayableFormattedString(quantityFromIntent, singleItemPrice));
            amountPayableTextView.setText(getTotalAmountPayableFormattedString(quantityFromIntent, singleItemPrice));
            totalAmountTextView.setText(getTotalAmountPayableFormattedString(quantityFromIntent, singleItemPrice));
        }
    }

    private String getTotalAmountPayableFormattedString(int quantityFromIntent, String singleItemPrice) {
        return String.format("%s %s", getString(R.string.rupees), String.valueOf(getTotalAmount(quantityFromIntent, singleItemPrice)));
    }

    private double getTotalAmount(int quantity, String singleItemPrice) {
        return quantity * Double.parseDouble(singleItemPrice);
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

    private Product getProductFromIntent() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(IntentExtras.PRODUCT)) {
            return (Product) intent.getExtras().get(IntentExtras.PRODUCT);
        }
        return null;
    }

    private int getQuantityFromIntent() {
        Intent intent = getIntent();
        int quantity = 0;
        if (intent != null && intent.hasExtra(IntentExtras.QUANTITY)) {
            quantity = intent.getIntExtra(IntentExtras.QUANTITY, 0);
        }
        Log.d(TAG, "getQuantityFromIntent: quantity from intent : " + quantity);
        return quantity;
    }
}
