package android.learn.com.tripmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import studios.codelight.smartloginlibrary.SmartCustomLoginListener;
import studios.codelight.smartloginlibrary.SmartLoginBuilder;
import studios.codelight.smartloginlibrary.SmartLoginConfig;
import studios.codelight.smartloginlibrary.users.SmartGoogleUser;
import studios.codelight.smartloginlibrary.users.SmartUser;

public class SignInActivity extends AppCompatActivity  {

    final boolean SUCCESS=true;
    SmartCustomLoginListener loginListener = new SmartCustomLoginListener() {
        @Override
        public boolean customSignin(SmartUser smartUser) {
            //do something with smartUser
            if(SUCCESS){
                return true;
            } else {
                return false;
            }
        }

        @Override
        public boolean customSignup(SmartUser smartUser) {
            //do something with smartUser
            if(SUCCESS){
                return true;
            } else {
                return false;
            }
        }

        @Override
        public boolean customUserSignout(SmartUser smartUser) {
            //do something with smartUser
            if(SUCCESS){
                return true;
            } else {
                return false;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up the login form.


        SmartLoginBuilder loginBuilder = new SmartLoginBuilder();

        Intent intent = loginBuilder.with(this)
                .isGoogleLoginEnabled(true)
                .isFacebookLoginEnabled(false)
                .isCustomLoginEnabled(true).setSmartCustomLoginHelper(loginListener)
                .build();
        startActivityForResult(intent, SmartLoginConfig.LOGIN_REQUEST);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Intent "data" contains the user object
        Intent homepageIntent;
        if(resultCode == SmartLoginConfig.GOOGLE_LOGIN_REQUEST){
            SmartGoogleUser user;
            try {
                user = data.getParcelableExtra(SmartLoginConfig.USER);
                //use this user object as per your requirement
                homepageIntent = new Intent(this,HomePage.class);
                startActivity(homepageIntent);

            }catch (Exception e){
                Log.e(getClass().getSimpleName(), e.getMessage());
            }
        }else if(resultCode == SmartLoginConfig.CUSTOM_LOGIN_REQUEST){
            SmartUser user = data.getParcelableExtra(SmartLoginConfig.USER);
            //use this user object as per your requirement
            homepageIntent = new Intent(this,HomePage.class);
            startActivity(homepageIntent);

        }else if(resultCode == RESULT_CANCELED){
            //Login Failed
        }
    }
}

