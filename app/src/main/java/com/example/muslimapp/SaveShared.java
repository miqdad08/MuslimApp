package com.example.muslimapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveShared {
    private static final String USER_SHARED = "user_shared";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String KELAMIN = "kelamin";
    private static final String AGGRE = "aggre";
    private static final String LOGIN = "status_login";

    private final SharedPreferences preferences;


    SaveShared (Context context){
        preferences = context.getSharedPreferences(USER_SHARED, Context.MODE_PRIVATE);
    }

    public void setUser(UserModel value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME, value.getName());
        editor.putString(EMAIL, value.getEmail());
        editor.putString(PASSWORD, value.getPassword());
        editor.putString(KELAMIN, value.getKelamin());
        editor.putString(AGGRE, value.getAggre());
        editor.putBoolean(LOGIN, value.isStatusLogin());
        editor.apply();
    }

    UserModel getUser(){
        UserModel model = new UserModel();
        model.setName(preferences.getString(NAME, ""));
        model.setEmail(preferences.getString(EMAIL, ""));
        model.setPassword(preferences.getString(PASSWORD, ""));
        model.setKelamin(preferences.getString(KELAMIN, ""));
        model.setAggre(preferences.getString(AGGRE, ""));
        model.setStatusLogin(preferences.getBoolean(LOGIN, false));
        return model;
    }
}
