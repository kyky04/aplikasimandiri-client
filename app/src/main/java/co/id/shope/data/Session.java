package co.id.shope.data;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;


import co.id.shope.activities.LoginActivity;
import co.id.shope.models.LoginResponse;


/**
 * Created by Comp on 7/29/2017.
 */

public class Session {
    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "daikin";

    private static final String IS_LOGIN = "IsLoged";
    private static final String IS_FIRST = "IsFisrt";

    public static final String KEY_USER = "user";
    public static final String ROLE = "role";


    public Session(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUser(LoginResponse user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);

        editor.putString(KEY_USER, json);

        editor.putBoolean(IS_LOGIN, true);

        editor.commit();

    }

    public LoginResponse getUser() {
        Gson gson = new Gson();
        String json = pref.getString(KEY_USER, "");
        LoginResponse obj = gson.fromJson(json, LoginResponse.class);
        return obj;
    }

    public void checkLogin() {
        if (isLoggedIn()) {

        }
    }

//    public void logoutUser() {
//        editor.clear();
//        editor.commit();
//        editor.putBoolean(IS_LOGIN, false);
//
//        Intent i = new Intent(_context, LoginActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        _context.startActivity(i);
//    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }


    public void setIsLogin(Boolean v) {
        editor.putBoolean(IS_LOGIN, v);
        editor.commit();
    }

    public boolean isFirst() {
        return pref.getBoolean(IS_FIRST, false);
    }


    public void setIsFisrt(Boolean v) {
        editor.putBoolean(IS_FIRST, v);
        editor.commit();
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        editor.putBoolean(IS_LOGIN, false);

        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }


}
