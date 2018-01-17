package com.example.vuclip.liqyd.ui.login.registration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuclip.liqyd.R;

/**
 * Created by Banty on 13/01/18.
 */

public class RegistrationFragment extends Fragment {

    public RegistrationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_registration_fragment, container, false);
    }
}
