package com.example.vuclip.liqyd.ui.login.login;

import com.example.vuclip.liqyd.helper.SharedPrefHelper;
import com.example.vuclip.liqyd.helper.SharedPrefKeys;
import com.example.vuclip.liqyd.user.UserConstants;
import com.example.vuclip.liqyd.user.UserManager;

/**
 * Created by Banty on 17/01/18.
 */

public class LoginPresenter implements ILoginPresenter{

    private final ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }


    public void login(String mobileNumber, String password) {
        if(UserManager.getInstance().validateUser(mobileNumber, password)) {
            loginView.loginSuccess();
            updateLoginStatusInSharedPref("true");
        } else {
            loginView.loginFailed();
            updateLoginStatusInSharedPref("false");
        }
    }

    @Override
    public void updateLoginStatusInSharedPref(String loginStatus) {
        SharedPrefHelper.putPref(SharedPrefKeys.LOGIN_STATUS, loginStatus);
    }

    @Override
    public boolean checkUserLogin() {
        return SharedPrefHelper.isTrue(SharedPrefKeys.LOGIN_STATUS, "false");
    }
}
