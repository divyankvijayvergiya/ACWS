package com.acws.acws.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.acws.acws.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by divyankvijayvergiya on 04/02/18.
 */

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this , SignupAndLoginActivity.class);
                startActivity(intent);
            }
        };

        Timer timer = new Timer();
        timer.schedule(tt, 3000);
    }
}
