package com.example.vuclip.liqyd.user;

import android.text.TextUtils;

import com.example.vuclip.liqyd.helper.SharedPrefHelper;
import com.example.vuclip.liqyd.helper.SharedPrefKeys;

/**
 * Created by Banty on 17/01/18.
 */

public class UserManager {
    private static UserManager mUserManager;

    private UserManager() {

    }

    public static UserManager getInstance() {
        if (mUserManager == null) {
            mUserManager = new UserManager();
        }
        return mUserManager;
    }

    public static boolean validateUser(String mobileNumber, String password) {
        //hardcoding for now
        return TextUtils.equals(mobileNumber, SharedPrefHelper.getPref(UserConstants.USER_MOBILE, null)) &&
                TextUtils.equals(password, SharedPrefHelper.getPref(UserConstants.USER_PASSWORD, null));
    }

    public static void updateUserProperties(String name, String mobile, String email, String password) {
        SharedPrefHelper.putPref(UserConstants.USER_NAME, name);
        SharedPrefHelper.putPref(UserConstants.USER_MOBILE, mobile);
        SharedPrefHelper.putPref(UserConstants.USER_EMAIL, email);
        SharedPrefHelper.putPref(UserConstants.USER_PASSWORD, password);
    }

    /**
     * loginStatus = "true" -> login successful
     * loginStatus = "false" -> login successful
     */
    public void updateLoginStatusInSharedPref(String loginStatus) {
        SharedPrefHelper.putPref(SharedPrefKeys.LOGIN_STATUS, loginStatus);
    }

    /**
     * Set the login status of user as false
     * Clear all the previously set user properties
     */
    public void logoutUser() {
        updateLoginStatusInSharedPref("false");
    }


    public void saveUserAddress(String addressLine1, String addressLine2, String landmark) {
        SharedPrefHelper.putPref(UserConstants.USER_ADDRESS_1, addressLine1);
        SharedPrefHelper.putPref(UserConstants.USER_ADDRESS_2, addressLine2);
        SharedPrefHelper.putPref(UserConstants.USER_LANDMARK, landmark);
    }

    public boolean defaultAddressSet() {
        return SharedPrefHelper.getPref(UserConstants.USER_ADDRESS_1, null) != null &&
                SharedPrefHelper.getPref(UserConstants.USER_ADDRESS_2, null) != null &&
                SharedPrefHelper.getPref(UserConstants.USER_LANDMARK, null) != null;
    }
}
