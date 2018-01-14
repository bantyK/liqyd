package com.example.vuclip.liqyd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.helper.NavigationDrawerHelper;
import com.example.vuclip.liqyd.ui.gallary.OrderHistoryFragment;
import com.example.vuclip.liqyd.ui.gallary.TermsConditionsFragment;

/**
 * Created by Banty on 14/01/18.
 */

public class MenuHandingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu_handler);

        Intent intent = getIntent();
        int navIndex = 0;
        if (intent != null && intent.getExtras().containsKey(NavigationDrawerHelper.INTENT_KEY)) {
            navIndex = intent.getIntExtra(NavigationDrawerHelper.INTENT_KEY, 0);
            setFragment(navIndex);
        }
        configureToolbar(getTag(navIndex), true);
    }

    private void setFragment(int navIndex) {

        Fragment fragment = getFragment(navIndex);
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.fragment_container, fragment, getTag(navIndex));
            fragmentTransaction.commitAllowingStateLoss();
        }

    }

    private String getTag(int navIndex) {
        switch (navIndex) {
            case 0:
                return NavigationDrawerHelper.TAG_ORDER_HISTORY;
            case 1:
                return NavigationDrawerHelper.TAG_TERMS_CONDITIONS;
            default:
                return NavigationDrawerHelper.TAG_TERMS_CONDITIONS;
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_menu_handler;
    }

    public Fragment getFragment(int navItemIndex) {
        switch (navItemIndex) {
            case 0:
                return new OrderHistoryFragment();
            case 1:
                return new TermsConditionsFragment();
            case 2:
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show();
                //sign out user here
                return null;
        }
        return null;
    }
}
