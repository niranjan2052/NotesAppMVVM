package com.myproject.notesappnvvm.Model.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.myproject.notesappnvvm.Model.Beans.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getAllTasks();

    @Insert
    void insertTasks(Task... tasks);

    @Query("DELETE FROM task_table WHERE id=:id")
    void deleteTask(int id);

    @Update
    void updateTask(Task task);
}
