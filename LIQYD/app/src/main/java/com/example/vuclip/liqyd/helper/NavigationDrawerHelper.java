package com.example.vuclip.liqyd.helper;

import android.app.Activity;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.ui.gallary.GallaryActivity;
import com.example.vuclip.liqyd.ui.gallary.OrderHistoryFragment;
import com.example.vuclip.liqyd.ui.gallary.TermsConditionsFragment;

/**
 * Created by Banty on 14/01/18.
 * Handles the Navigation Drawer functionality.
 */

public class NavigationDrawerHelper {
    //fragment tags
    private static final String TAG_ORDER_HISTORY = "order_history";
    private static final String TAG_TERMS_CONDITIONS = "terms_conditions";
    private final FragmentManager fragmentManager;
    private final DrawerLayout drawer;
    private final NavigationView navigationView;
    private final Toolbar toolbar;
    private Handler mHandler;
    private String CURRENT_TAG;
    private int navItemIndex = 0;
    private final Activity mContext;


    public NavigationDrawerHelper(Activity context, FragmentManager supportFragmentManager, DrawerLayout drawer, NavigationView navigationView, Toolbar toolbar) {
        mContext = context;
        this.fragmentManager = supportFragmentManager;
        this.drawer = drawer;
        mHandler = new Handler();
        this.navigationView = navigationView;
        this.toolbar = toolbar;

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

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(mContext, drawer, toolbar, R.string.open_drawer, R.string.close_drawer) {

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

        if (fragmentManager.findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getFragment();
                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                            android.R.anim.fade_out);
                    fragmentTransaction.replace(R.id.fragment_container, fragment, CURRENT_TAG);
                    fragmentTransaction.commitAllowingStateLoss();
                }
            }
        };

        mHandler.post(mPendingRunnable);

        drawer.closeDrawers();

        mContext.invalidateOptionsMenu();

    }

    @Nullable
    private Fragment getFragment() {
        switch (navItemIndex) {
            case 0:
                return new OrderHistoryFragment();
            case 1:
                return new TermsConditionsFragment();
            case 2:
                Toast.makeText(mContext, "Sign out clicked", Toast.LENGTH_SHORT).show();
                //sign out user here
                return null;
        }
        return null;
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setToolbarTitle() {
        ActionBar supportActionBar = ((GallaryActivity) mContext).getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.setTitle("Fragment : " + navItemIndex);
    }

}
