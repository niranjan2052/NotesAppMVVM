package com.myproject.notesappnvvm.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myproject.notesappnvvm.R;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    CheckBox chkLoggedIn;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.userID);
        password = findViewById(R.id.userPassword);
        chkLoggedIn = findViewById(R.id.chkLoggedIn);
        loginButton = findViewById(R.id.loginBtn);

        Intent iHome = new Intent(LoginActivity.this, MainActivity.class);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");

        if (checkbox.equals("true")) {
            startActivity(iHome);
            finish();
        }

        loginButton.setOnClickListener(view -> {
            if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    startActivity(iHome);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Please!! fill username and password", Toast.LENGTH_SHORT).show();
            }

        });

        chkLoggedIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                } else {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                }
            }
        });
    }
}