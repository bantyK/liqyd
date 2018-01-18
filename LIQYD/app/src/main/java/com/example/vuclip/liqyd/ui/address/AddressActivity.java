package com.example.vuclip.liqyd.ui.address;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.ui.BaseActivity;

public class AddressActivity extends BaseActivity {

    private static final String TAG = "AddressActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_address_activity);
        configureToolbar(getString(R.string.address), true);
        initUIElements();
    }

    private void initUIElements() {
        ImageView backButton = findViewById(R.id.iv_back_button);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_back_button:
                        finish();
                        break;
                }
            }
        };

        backButton.setOnClickListener(clickListener);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_address_activity;
    }
}
