package com.example.vuclip.liqyd.ui.login.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuclip.liqyd.R;
import com.example.vuclip.liqyd.adapters.GallaryAdapter;
import com.example.vuclip.liqyd.ui.gallary.GallaryActivity;

/**
 * Created by Banty on 13/01/18.
 */

public class LoginFragment extends Fragment implements ILoginView {

    private static final String TAG = "LoginFragment";

    private Button loginButton, registerButton;
    private EditText mobileNumberEditText, passwordEditText;
    private TextView forgotPasswordTextView;
    private View.OnClickListener clickListener;

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
        loginButton = view.findViewById(R.id.btn_login);
        registerButton = view.findViewById(R.id.btn_register);
        mobileNumberEditText = view.findViewById(R.id.et_mobile_number);
        passwordEditText = view.findViewById(R.id.et_password);
        forgotPasswordTextView = view.findViewById(R.id.tv_forgot_password);

        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_login:
                        String mobileNumber = mobileNumberEditText.getText().toString();
                        String password = passwordEditText.getText().toString();
                        mLoginPresenter.login(mobileNumber, password);
                        break;
                    case R.id.btn_register:
                        Log.d(TAG, "onClick: register button clicked");
                        break;
                }
            }
        };

        loginButton.setOnClickListener(clickListener);
        registerButton.setOnClickListener(clickListener);
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
