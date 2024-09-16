package com.myproject.notesappnvvm.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.myproject.notesappnvvm.Model.Beans.Task;
import com.myproject.notesappnvvm.Model.Dao.TaskDao;
import com.myproject.notesappnvvm.Model.Database.AppDatabase;

import java.util.List;

public class TaskRepository {

    public TaskDao taskDao;
    public LiveData<List<Task>> allTasks;

    public TaskRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabaseInstance(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    public void insertTask(Task task) {
        new Thread(() -> taskDao.insertTasks(task)).start();
    }

    public void deleteTask(int id) {
        new Thread(() -> taskDao.deleteTask(id)).start();
    }

    public void updateTask(Task task) {
        new Thread(() -> taskDao.updateTask(task)).start();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
}
