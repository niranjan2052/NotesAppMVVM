package com.myproject.notesappnvvm.View.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myproject.notesappnvvm.Helper.HelperClass;
import com.myproject.notesappnvvm.Model.Beans.Task;
import com.myproject.notesappnvvm.Repository.TaskRepository;
import com.myproject.notesappnvvm.ViewModel.TaskViewModel;
import com.myproject.notesappnvvm.databinding.ActivityInsertTaskBinding;

import java.util.Calendar;
import java.util.Date;

public class InsertTaskActivity extends AppCompatActivity {

    ActivityInsertTaskBinding binding;
    String title, description;
    Date dueDate;
    boolean isCompleted;
    DatePickerDialog datePickerDialog;
    TaskRepository taskRepository;

    TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskRepository = new TaskRepository(this.getApplication());
//        HelperClass.initDatePicker(this, datePickerDialog, binding.btnDueDatePicker);
        initDatePicker();

        binding.btnDueDatePicker.setText(HelperClass.DateToString(new Date()));
        binding.btnDueDatePicker.setOnClickListener(this::openDatePicker);

        binding.btnTaskAddAction.setOnClickListener(view -> {
            title = binding.edtCardTitle.getText().toString();
            description = binding.edtCardDescription.getText().toString();
            dueDate = HelperClass.StringToDate(binding.btnDueDatePicker.getText().toString());
            isCompleted = binding.chkStatusBox.isChecked();
            CreateTask(title, description, dueDate, isCompleted);
            finish();
        });
    }

    private void CreateTask(String title, String description, Date dueDate, boolean isCompleted) {
        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please Fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            taskRepository.insertTask(new Task(title, description, dueDate, isCompleted));
        }
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = HelperClass.makeDateString(day, month, year);
            binding.btnDueDatePicker.setText(HelperClass.getTodayDate());
            binding.btnDueDatePicker.setText(date);
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 10000);
    }

}