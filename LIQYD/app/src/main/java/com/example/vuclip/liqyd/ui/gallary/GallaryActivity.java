package com.example.vuclip.liqyd.ui.gallary;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.helper.NavigationDrawerHelper;
import com.example.vuclip.liqyd.ui.BaseActivity;

import java.util.Arrays;
import java.util.List;

public class GallaryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gallary);
        initDrawer();
        initSpinner();
    }

    private void initSpinner() {
        Spinner spinner = findViewById(R.id.delivery_area_spinner);
        List<String> deliveryAreas = Arrays.asList(getResources().getStringArray(R.array.delivery_areas));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_text, deliveryAreas);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinner.setAdapter(adapter);
    }

    private void initDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new NavigationDrawerHelper(this,
                getSupportFragmentManager(),
                drawer,
                navigationView
        ).setUpNavigationMenu(navigationView, drawer);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_gallary;
    }

}
