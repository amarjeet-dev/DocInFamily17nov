package com.e.docinfamily17nov.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DocAppSharedPreference {
    String PREF_NAME = "doc";
String access_token="access_token";

    private SharedPreferences pref = null;
    private static DocAppSharedPreference preferences = null;
    static Context mContext = null;

    public static DocAppSharedPreference getInstance(Context context) {
        if (preferences == null) {
            preferences = new DocAppSharedPreference(context);
        }
        mContext = context;
        return preferences;
    }

    public void clearPreference() {
        pref.edit().clear().commit();
    }

    public DocAppSharedPreference(Context context) {
        mContext = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
    public DocAppSharedPreference(Activity context) {
        mContext = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
    //----------
    public void setAccess_token(String str) {
        pref.edit().putString(access_token, str).commit();
    }

    public String getAccess_token() {
        return pref.getString(access_token, "");
    }


    public static void printHashKey(Context pContext) {
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i( "printHashKey " ,hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e( "printHashKey()", e.toString());
        } catch (Exception e) {
            Log.e("printHashKey()", e.toString());
        }
    }
}
