package com.example.vuclip.liqyd.ui.order_details;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.helper.IntentExtras;
import com.example.vuclip.liqyd.models.Product;
import com.example.vuclip.liqyd.ui.BaseActivity;
import com.example.vuclip.liqyd.ui.payment.PaymentActivity;

import java.util.Arrays;
import java.util.List;

public class OrderDetailsActivity extends BaseActivity {
    private static final String TAG = "OrderDetailsActivity";
    private TextView orderQuantity, productName, productPrice, totalPrice;
    private ImageView backButton, productImage;
    private Button proceedPayButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_order_details_activity);
        configureToolbar(getString(R.string.order_details), true);
        initUIElements();
        setUpSpinners();

    }

    private Product getProductFromIntent() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(IntentExtras.PRODUCT)) {
            return (Product) intent.getExtras().get(IntentExtras.PRODUCT);
        }
        return null;
    }

    private void initUIElements() {
        Button quantityPlusButton = findViewById(R.id.quantity_plus_button);
        Button quantityMinusButton = findViewById(R.id.quantity_minus_button);
        orderQuantity = findViewById(R.id.order_quantity);
        backButton = findViewById(R.id.iv_back_button);
        productImage = findViewById(R.id.product_image);
        productPrice = findViewById(R.id.product_price);
        productName = findViewById(R.id.product_name);
        totalPrice = findViewById(R.id.total_price);
        proceedPayButton = findViewById(R.id.proceed_to_pay);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantityValue = Integer.parseInt(orderQuantity.getText().toString());
                switch (v.getId()) {
                    case R.id.quantity_plus_button:
                        currentQuantityValue = currentQuantityValue + 1;
                        updateTotalCost(currentQuantityValue);
                        break;
                    case R.id.quantity_minus_button:
                        if (currentQuantityValue != 2)
                            currentQuantityValue = currentQuantityValue - 1;
                        updateTotalCost(currentQuantityValue);
                        break;
                    case R.id.iv_back_button:
                        Log.d(TAG, "onClick: back button clicked");
                        finish();
                        break;
                    case R.id.proceed_to_pay:
                        launchPaymentActivity(getProductFromIntent());
                        break;
                }

                orderQuantity.setText(String.valueOf(currentQuantityValue));
            }
        };
        quantityPlusButton.setOnClickListener(clickListener);
        quantityMinusButton.setOnClickListener(clickListener);
        backButton.setOnClickListener(clickListener);
        proceedPayButton.setOnClickListener(clickListener);

        //populate the UI elements with the product details
        setUpUI(getProductFromIntent());
    }

    private void launchPaymentActivity(Product product) {
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(IntentExtras.PRODUCT, product);
        startActivity(intent);
    }

    private void updateTotalCost(int currentQuantityValue) {
        double price = Double.parseDouble(getProductFromIntent().getPrice());
        double newPrice = price * currentQuantityValue;
        setPrice(String.valueOf(newPrice), totalPrice);
    }


    public void setUpSpinners() {
        setUpDeliverySlotAdapter((Spinner) findViewById(R.id.delivery_slot_spinner), Arrays.asList(getResources().getStringArray(R.array.delivery_slots)));
        setUpDeliverySlotAdapter((Spinner) findViewById(R.id.delivery_type_spinner), Arrays.asList(getResources().getStringArray(R.array.delivery_types)));
        setUpDeliverySlotAdapter((Spinner) findViewById(R.id.delivery_time_spinner), Arrays.asList(getResources().getStringArray(R.array.delivery_time)));
    }

    private void setUpDeliverySlotAdapter(Spinner spinner, List<String> spinnerData) {
        ArrayAdapter<String> deliverySlotAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerData);
        spinner.setAdapter(deliverySlotAdapter);
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.layout_order_details_activity;
    }

    public void setUpUI(Product product) {
        if (productName != null && productPrice != null && productImage != null) {
            productName.setText(product.getName());
            setPrice(product.getPrice(), productPrice);
            productImage.setImageResource(product.getDrawableImage());
            setPrice(getInitialPrice(product.getPrice()), totalPrice);
        }
    }

    private String getInitialPrice(String price) {
        return String.valueOf(Double.parseDouble(price) * 2);
    }

    private void setPrice(String price, TextView view) {
        if (view != null)
            view.setText(String.format("%s %s", getString(R.string.rupees), price));
    }
}
