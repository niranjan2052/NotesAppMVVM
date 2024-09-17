package com.myproject.notesappnvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.myproject.notesappnvvm.Model.Beans.Task;
import com.myproject.notesappnvvm.Repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    public TaskRepository repository;
    public LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);

        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }

    public void insertTask(Task task) {
        repository.insertTask(task);
    }

    public void deleteTask(int id) {
        repository.deleteTask(id);
    }

    public void updateTask(Task task) {
        repository.updateTask(task);
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
}
