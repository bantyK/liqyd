package com.example.vuclip.liqyd.ui.gallary;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.ui.BaseActivity;

public class GallaryActivity extends BaseActivity {

    //fragment tags
    private static final String TAG_ORDER_HISTORY = "order_history";
    private static final String TAG_TERMS_CONDITIONS = "terms_conditions";
    private String CURRENT_TAG;

    private Handler mHandler;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private View navigationHeader;
    private TextView navHeaderName;
    private TextView navHeaderMobile;
    private Toolbar toolbar;
    private int navItemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gallary_activity);
        mHandler = new Handler();
        initDrawer();

    }

    private void initDrawer() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        //navigation view header
        navigationHeader = navigationView.getHeaderView(0);
        navHeaderName = navigationHeader.findViewById(R.id.nav_name);
        navHeaderMobile = navigationHeader.findViewById(R.id.nav_mobile);

        loadNavigationHeader();

        setUpNavigationMenu();

    }

    public void setUpNavigationMenu() {
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

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer) {

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
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    private void loadFragment() {
        selectNavMenu();

        setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getFragment();
                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                            android.R.anim.fade_out);
                    fragmentTransaction.replace(R.id.fragment_container, fragment, CURRENT_TAG);
                    fragmentTransaction.commitAllowingStateLoss();
                }
            }
        };

        mHandler.post(mPendingRunnable);

        drawer.closeDrawers();

        invalidateOptionsMenu();

    }

    @Nullable
    private Fragment getFragment() {
        switch (navItemIndex) {
            case 0:
                return new OrderHistoryFragment();
            case 1:
                return new TermsConditionsFragment();
            case 2:
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show();
                return null;
        }
        return null;
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle("Fragment : " + navItemIndex);
    }

    private void loadNavigationHeader() {
        navHeaderName.setText("banty");
        navHeaderMobile.setText("1234567890");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_gallary_activity;
    }

}
