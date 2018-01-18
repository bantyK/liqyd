package com.example.vuclip.liqyd.ui.login.login;

import android.text.TextUtils;

import com.example.vuclip.liqyd.helper.SharedPrefHelper;
import com.example.vuclip.liqyd.helper.SharedPrefKeys;
import com.example.vuclip.liqyd.user.UserConstants;
import com.example.vuclip.liqyd.user.UserManager;

/**
 * Created by Banty on 17/01/18.
 */

public class LoginPresenter implements ILoginPresenter {

    private final ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }


    public void login(String mobileNumber, String password) {
        if (UserManager.getInstance().validateUser(mobileNumber, password)) {
            loginView.loginSuccess();
            UserManager.getInstance().updateLoginStatusInSharedPref("true");
        } else {
            loginView.loginFailed();
            UserManager.getInstance().updateLoginStatusInSharedPref("false");
        }
    }

    @Override
    public boolean checkUserLogin() {
        return SharedPrefHelper.isTrue(SharedPrefKeys.LOGIN_STATUS, "false");
    }

    @Override
    public boolean validEntries(String mobileNumber, String password) {
        return !(TextUtils.isEmpty(mobileNumber) && TextUtils.isEmpty(password));
    }
}
