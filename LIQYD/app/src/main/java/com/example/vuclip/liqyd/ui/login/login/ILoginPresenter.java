package com.example.vuclip.liqyd.ui.login.login;

/**
 * Created by Banty on 17/01/18.
 */

public interface ILoginPresenter {
    void login(String mobile, String password);

    void updateLoginStatusInSharedPref(String loggedIn);

    boolean checkUserLogin();
}
