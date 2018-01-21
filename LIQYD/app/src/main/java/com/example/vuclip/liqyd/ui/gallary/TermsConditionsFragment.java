package com.example.vuclip.liqyd.ui.gallary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vuclip.liqyd.R;

/**
 * Created by Banty on 14/01/18.
 */

public class TermsConditionsFragment extends Fragment {


    public TermsConditionsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_terms_conditions, container, false);
        return view;
    }
}
