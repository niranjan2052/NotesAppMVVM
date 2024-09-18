package com.myproject.notesappnvvm.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.notesappnvvm.Model.Beans.User;
import com.myproject.notesappnvvm.R;
import com.myproject.notesappnvvm.Repository.UserRepository;
import com.myproject.notesappnvvm.ViewModel.UserViewModel;
import com.myproject.notesappnvvm.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    UserViewModel userViewModel;
    String username, password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.loginLink.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
        });

        binding.SignUpBtn.setOnClickListener(view -> {
            username = binding.edtSignUpUsername.getText().toString();
            password = binding.edtSignupUserPassword.getText().toString();
            email = binding.edtSignupUserEmail.getText().toString();
            createUser(username, email, password);
        });
    }

    public void createUser(String username, String email, String password) {
//        Log.d("UserInfo", "onCreate: username: " + username + " email: " + email + " password: " + password);
        if (!userViewModel.username_taken(username)) {
            userViewModel.insertUser(new User(username, email, password, true));
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Username Taken", Toast.LENGTH_SHORT).show();
        }
    }
}