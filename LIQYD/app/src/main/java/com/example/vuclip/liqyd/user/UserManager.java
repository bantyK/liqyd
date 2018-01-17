package com.example.vuclip.liqyd.user;

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
        return mobileNumber.equalsIgnoreCase("123") &&
                password.equals("test");
    }
}
