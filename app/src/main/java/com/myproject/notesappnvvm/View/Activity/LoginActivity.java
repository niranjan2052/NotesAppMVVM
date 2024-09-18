package com.myproject.notesappnvvm.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.notesappnvvm.R;
import com.myproject.notesappnvvm.ViewModel.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    CheckBox chkLoggedIn;
    Button loginButton;
    TextView loginToSignUp;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.userID);
        password = findViewById(R.id.userPassword);
        chkLoggedIn = findViewById(R.id.chkLoggedIn);
        loginButton = findViewById(R.id.loginBtn);
        loginToSignUp = findViewById(R.id.signUpLink);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        Intent iHome = new Intent(LoginActivity.this, MainActivity.class);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");

        if (checkbox.equals("true")) {
            startActivity(iHome);
            finish();
        }

        loginToSignUp.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });

        loginButton.setOnClickListener(view -> {
            if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    startActivity(iHome);
                    finish();
                } else if (userViewModel.allow_login(username.getText().toString(), password.getText().toString())) {
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