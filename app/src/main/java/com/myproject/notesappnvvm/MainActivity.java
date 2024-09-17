package com.myproject.notesappnvvm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.myproject.notesappnvvm.Activity.InsertTaskActivity;
import com.myproject.notesappnvvm.Activity.LoginActivity;
import com.myproject.notesappnvvm.Adapter.TaskRecyclerViewAdapter;
import com.myproject.notesappnvvm.ViewModel.TaskViewModel;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newTaskBtn;
    TaskViewModel taskViewModel;
    RecyclerView recyclerTaskList;
    TaskRecyclerViewAdapter adapter;
    ImageView ivMenuBtn;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newTaskBtn = findViewById(R.id.addTaskBtn);
        recyclerTaskList = findViewById(R.id.recyclerTaskList);
        ivMenuBtn = findViewById(R.id.menu_icon);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        toolbar = findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        ivMenuBtn.setOnClickListener(view -> {
            drawerLayout.openDrawer(GravityCompat.END);
        });

        newTaskBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, InsertTaskActivity.class));
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_home) {
                    item.setChecked(true);
                    drawerLayout.closeDrawers();
                    return true;
                } else if (itemId == R.id.menu_logout) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    drawerLayout.closeDrawers();
                    Intent iLogin = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(iLogin);
                    finish();
                    return true;
                } else if (itemId == R.id.menu_exit) {
                    finishAffinity();
                    return true;
                }
                return false;
            }
        });

        taskViewModel.getAllTasks().observe(this, tasks -> {
            recyclerTaskList.setLayoutManager(new LinearLayoutManager(this));
            adapter = new TaskRecyclerViewAdapter(MainActivity.this, tasks);
            recyclerTaskList.setAdapter(adapter);
        });

    }
}