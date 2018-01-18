package com.example.vuclip.liqyd.ui.order_details;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.ui.BaseActivity;

import java.util.Arrays;
import java.util.List;

public class OrderDetailsActivity extends BaseActivity {
    private static final String TAG = "OrderDetailsActivity";
    private TextView orderQuantity;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_order_details_activity);
        configureToolbar(getString(R.string.order_details), true);
        initUIElements();
        setUpSpinners();

    }

    private void initUIElements() {
        Button quantityPlusButton = findViewById(R.id.quantity_plus_button);
        Button quantityMinusButton = findViewById(R.id.quantity_minus_button);
        orderQuantity = findViewById(R.id.order_quantity);
        backButton = findViewById(R.id.iv_back_button);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantityValue = Integer.parseInt(orderQuantity.getText().toString());
                switch (v.getId()) {
                    case R.id.quantity_plus_button:
                        currentQuantityValue = currentQuantityValue + 1;
                        break;
                    case R.id.quantity_minus_button:
                        if (currentQuantityValue != 2)
                            currentQuantityValue = currentQuantityValue - 1;
                        break;
                    case R.id.iv_back_button:
                        Log.d(TAG, "onClick: back button clicked");
                        finish();
                        break;
                }

                orderQuantity.setText(String.valueOf(currentQuantityValue));
            }
        };
        quantityPlusButton.setOnClickListener(clickListener);
        quantityMinusButton.setOnClickListener(clickListener);
        backButton.setOnClickListener(clickListener);

    }


    public void setUpSpinners() {
        setUpDeliverySlotAdapter((Spinner) findViewById(R.id.delivery_slot_spinner), Arrays.asList(getResources().getStringArray(R.array.delivery_slots)));
        setUpDeliverySlotAdapter((Spinner) findViewById(R.id.delivery_type_spinner), Arrays.asList(getResources().getStringArray(R.array.delivery_types)));
        setUpDeliverySlotAdapter((Spinner) findViewById(R.id.delivery_time_spinner), Arrays.asList(getResources().getStringArray(R.array.delivery_time)));
    }

    private void setUpDeliverySlotAdapter(Spinner spinner, List<String> spinnerData) {
        ArrayAdapter<String> deliverySlotAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerData);
        spinner.setAdapter(deliverySlotAdapter);
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.layout_order_details_activity;
    }
}
