package com.example.vuclip.liqyd.ui.login.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.ui.gallary.GallaryActivity;

/**
 * Created by Banty on 13/01/18.
 */

public class LoginFragment extends Fragment implements ILoginView {

    public static final String TAG = "LoginFragment";

    private EditText mobileNumberEditText;
    private EditText passwordEditText;

    private LoginPresenter mLoginPresenter;

    public LoginFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_login_fragment, container, false);
        initUIElements(view);
        registerPresenter();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mLoginPresenter.checkUserLogin()) {
            loginSuccess();
        }
    }

    private void initUIElements(View view) {
        Button loginButton = view.findViewById(R.id.btn_login);
        mobileNumberEditText = view.findViewById(R.id.et_mobile_number);
        passwordEditText = view.findViewById(R.id.et_password);
        TextView forgotPasswordTextView = view.findViewById(R.id.tv_forgot_password);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_login:
                        String mobileNumber = mobileNumberEditText.getText().toString();
                        String password = passwordEditText.getText().toString();
                        if (mLoginPresenter.validEntries(mobileNumber, password))
                            mLoginPresenter.login(mobileNumber, password);
                        else
                            Toast.makeText(getContext(), "Please fill in all the details", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.tv_forgot_password:
                        //handle forgot password here
                        break;
                }
            }
        };

        loginButton.setOnClickListener(clickListener);
        forgotPasswordTextView.setOnClickListener(clickListener);
    }

    @Override
    public void registerPresenter() {
        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    public void loginSuccess() {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), GallaryActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void loginFailed() {
        if (getActivity() != null)
            Toast.makeText(getActivity(), "Login Failed, please try again", Toast.LENGTH_SHORT).show();
    }
}
