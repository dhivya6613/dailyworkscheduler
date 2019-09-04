package com.example.dailyworkscheduler.data;

import android.util.Log;
import android.widget.Toast;

import com.example.dailyworkscheduler.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public static final String TAG = "LOGIN_FORM";
    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            //thirumagaldhivya123@gmail.com
            //abcde

            if("dhivya".equals(username) && "test1234".equals(password)) {
                LoggedInUser fakeUser =
                        new LoggedInUser(
                                java.util.UUID.randomUUID().toString(),
                                "Thirumagal Dhivya");
                return new Result.Success<>(fakeUser);
            } else {
                throw new Exception("Invalid username or password !");
            }
        } catch (Exception e) {
            Log.e(TAG, "Invalid username or password. Please try again.", e);
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
