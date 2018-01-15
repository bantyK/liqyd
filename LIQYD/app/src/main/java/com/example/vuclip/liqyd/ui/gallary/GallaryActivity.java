package com.example.vuclip.liqyd.ui.gallary;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.adapters.GallaryAdapter;
import com.example.vuclip.liqyd.helper.NavigationDrawerHelper;
import com.example.vuclip.liqyd.models.Product;
import com.example.vuclip.liqyd.repository.DataProvider;
import com.example.vuclip.liqyd.repository.Repository;
import com.example.vuclip.liqyd.ui.BaseActivity;

import java.util.Arrays;
import java.util.List;

public class GallaryActivity extends BaseActivity {

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gallary);
        initDrawer();
        initSpinner();
        displayProducts();
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.open_drawer, R.string.close_drawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
        new NavigationDrawerHelper(this,
                drawer,
                navigationView,
                mActionBarDrawerToggle
        ).setUpNavigationMenu(navigationView, drawer);

    }

    private void displayProducts() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        GallaryAdapter adapter = new GallaryAdapter(new Repository().getDataProvider(), this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_gallary;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
