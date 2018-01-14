package com.example.vuclip.liqyd.ui.gallary;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.helper.NavigationDrawerHelper;
import com.example.vuclip.liqyd.ui.BaseActivity;

public class GallaryActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gallary_activity);
        initDrawer();
    }

    private void initDrawer() {
        //initializes the drawer layout.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        new NavigationDrawerHelper(this,
                getSupportFragmentManager(),
                drawer,
                navigationView,
                toolbar
        ).setUpNavigationMenu(navigationView, drawer);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_gallary_activity;
    }

}
