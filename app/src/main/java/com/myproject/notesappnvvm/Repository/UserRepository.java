package com.myproject.notesappnvvm.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.myproject.notesappnvvm.Model.Beans.User;
import com.myproject.notesappnvvm.Model.Dao.UserDao;
import com.myproject.notesappnvvm.Model.Database.AppDatabase;

import java.util.List;

public class UserRepository {
    public UserDao userDao;
    public LiveData<List<User>> getAllUsers;

    public UserRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getDatabaseInstance(application);
        userDao = appDatabase.userDao();
        getAllUsers = userDao.getAllUsers();
    }

    public void insertUser(User user) {
        userDao.insertUsers(user);
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
