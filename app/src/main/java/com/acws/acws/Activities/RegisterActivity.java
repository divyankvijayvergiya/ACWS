package com.acws.acws.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.acws.acws.Model.User;
import com.acws.acws.R;
import com.acws.acws.SharedPrefManager;
import com.acws.acws.VolleySingleton;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by divyankvijayvergiya on 23/02/18.
 */

public class RegisterActivity extends AppCompatActivity {
    EditText etEmail, etPassword, etConfPassword, etContact, etName;
    Button btRegister;
    TextView tvToLogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.et_email_register);
        etPassword = findViewById(R.id.et_password_register);
        etConfPassword = findViewById(R.id.et_conf_password_register);
        etContact = findViewById(R.id.contact_password_register);
        btRegister = findViewById(R.id.bt_register);
        tvToLogin = findViewById(R.id.text_to_login);
        etName = findViewById(R.id.et_name);
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();

            }
        });

        tvToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });


    }

    private void registerUser() {
        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        final String confPassword = etConfPassword.getText().toString().trim();
        final String contact = etContact.getText().toString().trim();
        final String name = etName.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Please enter your email!");
            etEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(confPassword)) {
            etConfPassword.setError("Please confirm your password");
            etConfPassword.requestFocus();
            return;
        }
        if (password == confPassword) {
            etConfPassword.setError("Password does not match");
            etConfPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(contact)) {
            etContact.setError("Please enter your contact no.");
            etContact.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(name)) {
            etName.setError("Please enter your Name");
            etName.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ServerConfig.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.d("--->", response.toString());
                            //SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("users");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String date = jsonObject1.optString("date");
                                String email = jsonObject1.optString("email");
                                int id = jsonObject1.optInt("id");
                                String name = jsonObject1.optString("name");
                                String password = jsonObject1.optString("password");
                                String phone = jsonObject1.optString("phone");
                                User user = new User(date, email, id, password, name, phone);
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                            }
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                params.put("phone",contact );
                params.put("name",name);
                return params;

            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

}
