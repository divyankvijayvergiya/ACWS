package com.acws.acws;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.acws.acws.Activities.LoginActivity;
import com.acws.acws.Model.User;

/**
 * Created by divyankvijayvergiya on 22/02/18.
 */

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "Wheels_Play";
    private static final String KEY_DATE = "key_date";
    private static final String KEY_EMAIL = "key_email";
    private static final String KEY_ID = "key_id";
    private static final String KEY_PASSWORD ="key_pass";
    private static final String KEY_NAME = "key_name";
    private static final String KEY_PHONE = "key_phone";


    private static SharedPrefManager mInstance;
    private static Context mContext;

    private SharedPrefManager(Context mContext){
        this.mContext = mContext;
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if (mInstance == null){
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null) != null;
    }

    public void userLogin(User user){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_DATE, user.getDate());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putInt(KEY_ID,user.getId());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_PHONE, user.getPhone());

        editor.apply();
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_DATE, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_PASSWORD, null),
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_PHONE, null)
        );
    }

    public void logout() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }

}
