package presenter;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

import java.util.HashMap;

public class UserSessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    public static final String user_id = "userId";
    public static final String user_password = "userPassword";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    private static final String PREFER_NAME = "userDetails";


    public UserSessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserLoginSession(String luser_id, String luser_password) {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(user_id, luser_id);
        editor.putString(user_password, luser_password);
        editor.commit();
    }

    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(user_id, pref.getString(user_id, null));
        user.put(user_password, pref.getString(user_password, null));
        return user;
    }
}
