package com.example.vuclip.liqyd.helper;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.ui.MenuHandingActivity;
import com.example.vuclip.liqyd.ui.gallary.GallaryActivity;

/**
 * Created by Banty on 14/01/18.
 * Handles the Navigation Drawer functionality.
 */

public class NavigationDrawerHelper {
    private static final String TAG = "NavigationDrawerHelper";
    //fragment tags
    public static final String TAG_ORDER_HISTORY = "order_history";
    public static final String TAG_TERMS_CONDITIONS = "terms_conditions";
    public static final String INTENT_KEY = "intent.key";
    private final DrawerLayout drawer;
    private final NavigationView navigationView;
    private Handler mHandler;
    private String CURRENT_TAG;
    private int navItemIndex = 0;
    private final Activity detailActivity;


    public NavigationDrawerHelper(Activity context, DrawerLayout drawer, NavigationView navigationView) {
        detailActivity = context;
        this.drawer = drawer;
        mHandler = new Handler();
        this.navigationView = navigationView;
        loadNavigationHeader();
    }

    private void loadNavigationHeader() {
        View navigationHeader = navigationView.getHeaderView(0);
        TextView navHeaderName = navigationHeader.findViewById(R.id.nav_name);
        TextView navHeaderMobile = navigationHeader.findViewById(R.id.nav_mobile);

        navHeaderName.setText("banty");
        navHeaderMobile.setText("1234567890");
    }


    public void setUpNavigationMenu(NavigationView navigationView, final DrawerLayout drawer) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_order_history:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_ORDER_HISTORY;
                        break;

                    case R.id.nav_terms_conditions:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_TERMS_CONDITIONS;
                        break;

                    case R.id.nav_sign_out:
                        navItemIndex = 2;
                        break;
                    default:
                        navItemIndex = 0;
                }

                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }

                loadFragment();
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(detailActivity, drawer, R.string.open_drawer, R.string.close_drawer) {

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

        //Setting the actionbarToggle to drawer layout
        drawer.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else them hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        ActionBar supportActionBar = ((GallaryActivity) detailActivity).getSupportActionBar();

        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void loadFragment() {
        selectNavMenu();

        setToolbarTitle();

//        if (fragmentManager.findFragmentByTag(CURRENT_TAG) != null) {
//            drawer.closeDrawers();
//            return;
//        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Log.d(TAG, "run: current nav index : " + navItemIndex);
                Intent intent = new Intent(detailActivity, MenuHandingActivity.class);
                intent.putExtra(INTENT_KEY, navItemIndex);
                detailActivity.startActivity(intent);
            }
        };

        mHandler.post(mPendingRunnable);
        drawer.closeDrawers();
        detailActivity.invalidateOptionsMenu();

    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setToolbarTitle() {
        ActionBar supportActionBar = ((GallaryActivity) detailActivity).getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.setTitle("Fragment : " + navItemIndex);
    }

}
