package com.myproject.notesappnvvm.Model.Database;

import android.content.Context;

import com.myproject.notesappnvvm.Model.Beans.Task;
import com.myproject.notesappnvvm.Model.Beans.User;
import com.myproject.notesappnvvm.Model.Dao.TaskDao;
import com.myproject.notesappnvvm.Model.Dao.UserDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Task.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    public abstract UserDao userDao();

    public static AppDatabase INSTANCE;

    public static AppDatabase getDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "App_Database")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }
}
