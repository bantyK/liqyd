package com.example.vuclip.liqyd.activities.gallary;

import android.os.Bundle;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.activities.BaseActivity;

public class GallaryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gallary_activity);
        configureToolbar(getString(R.string.app_name));
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_gallary_activity;
    }

}
