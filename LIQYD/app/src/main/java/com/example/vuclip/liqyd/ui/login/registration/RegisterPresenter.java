package com.example.vuclip.liqyd.ui.login.registration;

import android.text.TextUtils;

import com.example.vuclip.liqyd.user.UserManager;

/**
 * Created by Banty on 18/01/18.
 */

class RegisterPresenter {

    private final IRegisterView registerView;

    public RegisterPresenter(IRegisterView registerView) {
        this.registerView = registerView;
    }

    public void registerUser(String name, String mobile, String email, String password) {
        UserManager.getInstance().updateUserProperties(name, mobile, email, password);
    }

    public boolean validEntries(String name, String mobile, String email, String password) {
        return !(TextUtils.isEmpty(name) &&
                TextUtils.isEmpty(mobile) &&
                TextUtils.isEmpty(email) &&
                TextUtils.isEmpty(password));
    }

    public void onRegistrationSuccess() {
        UserManager.getInstance().updateLoginStatusInSharedPref("true");
        registerView.showGallery();

    }
}
