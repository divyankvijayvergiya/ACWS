package com.acws.acws.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.acws.acws.R;

/**
 * Created by divyankvijayvergiya on 22/02/18.
 */

public class SignupAndLoginActivity extends AppCompatActivity {
    Button btRegister, btLogin;
    TextView or;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        btLogin = findViewById(R.id.bt_login);
        btRegister = findViewById(R.id.bt_register);
        or = findViewById(R.id.or);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(SignupAndLoginActivity.this, LoginActivity.class);
                startActivity(loginIntent);
             }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(SignupAndLoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}
