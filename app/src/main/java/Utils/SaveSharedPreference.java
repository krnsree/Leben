package Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import static Utils.PreferencesUtility.LOGGED_IN_PREF;

public class SaveSharedPreference {

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Login Status
     * @param context
     * @param loggedIn
     */
    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }

    /**
     * Get the Login Status
     * @param context
     * @return boolean: login status
     */
    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    public static void setAccount(Context context, GoogleSignInAccount account){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString("Account Name", account.getDisplayName());
        editor.apply();
    }

    public static String getAccount(Context context){
        return getPreferences(context).getString("Account Name", "");
    }

    public static void setAccountImage(Context context, GoogleSignInAccount account) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString("Account Image", String.valueOf(account.getPhotoUrl()));
        editor.apply();
    }

    public static String getAccountImage(Context context) {
        return getPreferences(context).getString("Account Image", "");
    }
}
