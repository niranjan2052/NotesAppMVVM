package com.myproject.notesappnvvm.View.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.myproject.notesappnvvm.Helper.HelperClass;
import com.myproject.notesappnvvm.Model.Beans.Task;
import com.myproject.notesappnvvm.Repository.TaskRepository;
import com.myproject.notesappnvvm.databinding.ActivityUpdateNotesBinding;

import java.util.Calendar;
import java.util.Date;

public class UpdateTaskActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    DatePickerDialog datePickerDialog;
    int id;
    String title, description;
    Date dueDate;
    Boolean isCompleted;
    TaskRepository taskRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        dueDate = (Date) getIntent().getSerializableExtra("dueDate");
        isCompleted = getIntent().getBooleanExtra("isCompleted", false);

        initDatePicker();

        binding.edtUpdateCardTitle.setText(title);
        binding.edtUpdateCardDescription.setText(description);
        binding.btnUpdateDueDatePicker.setText(HelperClass.DateToString(dueDate));
        binding.chkUpdateStatusBox.setChecked(isCompleted);

        binding.btnUpdateDueDatePicker.setOnClickListener(this::openDatePicker);

        taskRepository = new TaskRepository(this.getApplication());

        binding.btnTaskUpdateAction.setOnClickListener(view -> {
            String udTitle = binding.edtUpdateCardTitle.getText().toString();
            String udDescription = binding.edtUpdateCardDescription.getText().toString();
            Date udDueDate = HelperClass.StringToDate(binding.btnUpdateDueDatePicker.getText().toString());
            boolean udIsCompleted = binding.chkUpdateStatusBox.isChecked();

            UpdateTask(id, udTitle, udDescription, udDueDate, udIsCompleted);
        });

    }

    public void UpdateTask(int id, String title, String description, Date dueDate, boolean isCompleted) {
        taskRepository.updateTask(new Task(id, title, description, dueDate, isCompleted));
        Log.d("TaskValue", "UpdateTask: id:" + id + " title: " + title + " description: " + description + "dueDate: " + dueDate + "isCompleted: " + isCompleted);
//        Toast.makeText(this, "Task Updated Successfully", Toast.LENGTH_SHORT).show();
        finish();

    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = HelperClass.makeDateString(day, month, year);
            binding.btnUpdateDueDatePicker.setText(HelperClass.getTodayDate());
            binding.btnUpdateDueDatePicker.setText(date);
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