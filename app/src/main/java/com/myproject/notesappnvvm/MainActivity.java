package com.myproject.notesappnvvm;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myproject.notesappnvvm.Activity.InsertTaskActivity;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newTaskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newTaskBtn = findViewById(R.id.addTaskBtn);

        newTaskBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, InsertTaskActivity.class));
        });

    }
}