package android.learn.com.tripmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mainIntent = new Intent(SignUp.this,HomePage.class);
        SignUp.this.startActivity(mainIntent);
        SignUp.this.finish();

    }
}
