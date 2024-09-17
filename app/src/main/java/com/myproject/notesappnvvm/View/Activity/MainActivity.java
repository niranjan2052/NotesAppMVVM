package com.myproject.notesappnvvm.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
import com.myproject.notesappnvvm.R;
import com.myproject.notesappnvvm.View.Adapter.TaskRecyclerViewAdapter;
import com.myproject.notesappnvvm.Model.Beans.Task;
import com.myproject.notesappnvvm.ViewModel.TaskViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newTaskBtn;
    TaskViewModel taskViewModel;
    RecyclerView recyclerTaskList;
    TaskRecyclerViewAdapter adapter;
    ImageView ivMenuBtn;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    View emptyStateLayout;

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
        emptyStateLayout = findViewById(R.id.empty_state_layout);

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
            checkIfEmpty((ArrayList<Task>) tasks);
        });
    }
        public void checkIfEmpty(ArrayList<Task> items) {
            if (items.isEmpty()) {
                recyclerTaskList.setVisibility(View.GONE);
                emptyStateLayout.setVisibility(View.VISIBLE);
            } else {
                recyclerTaskList.setVisibility(View.VISIBLE);
                emptyStateLayout.setVisibility(View.GONE);
            }
        }

}