package com.example.vuclip.liqyd.ui.order_details;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.ui.BaseActivity;

import java.util.Arrays;
import java.util.List;

public class OrderDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_order_details_activity);
        configureToolbar(getString(R.string.order_details), true);

        setUpSpinners();
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
