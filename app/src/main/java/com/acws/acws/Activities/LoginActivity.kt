package com.acws.acws.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.acws.acws.R

/**
 * Created by divyankvijayvergiya on 08/02/18.
 */
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var etEmail = findViewById(R.id.et_email_login) as EditText
        var etPassword = findViewById(R.id.et_password_login) as EditText
        var btLogin = findViewById(R.id.bt_login) as Button
        var text_register = findViewById(R.id.text_to_register) as TextView

        btLogin.setOnClickListener{
            var email = etEmail.text.toString().trim();
            var password = etPassword.text.toString().trim();
            if(!email.isEmpty() && !password.isEmpty()){
                val mainIntent = Intent(this, MainActivity::class.java)
                startActivity(mainIntent)
            }
        }

    }
}