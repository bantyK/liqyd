package com.example.vuclip.liqyd.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuclip.liqyd.R;

public abstract class BaseActivity extends AppCompatActivity {

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
    }

    protected abstract int getLayoutResource();

    protected void configureToolbar(String toolbarTitle, boolean showBackButton) {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);
        TextView title = mToolbar.findViewById(R.id.title);
        ImageView backButton = mToolbar.findViewById(R.id.iv_back_button);
        if (showBackButton)
            backButton.setVisibility(View.VISIBLE);
        else
            backButton.setVisibility(View.GONE);
        title.setText(toolbarTitle);
    }

}
