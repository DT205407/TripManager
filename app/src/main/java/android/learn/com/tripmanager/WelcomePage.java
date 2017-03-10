package android.learn.com.tripmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            getSupportActionBar().hide();
        } catch (NullPointerException e) {

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
    }

    public void onsignupClick(View view)
    {
        Intent signUp=new Intent(this,SignUp.class);
        startActivity(signUp);
    }

    public void onsigninClick(View view)
    {
        Intent signIn=new Intent(this,SignInActivity.class);
        startActivity(signIn);
    }

}
