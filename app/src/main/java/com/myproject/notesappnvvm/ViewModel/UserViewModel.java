package com.myproject.notesappnvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.myproject.notesappnvvm.Model.Beans.User;
import com.myproject.notesappnvvm.Repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    public UserRepository userRepository;
    public LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers;
    }

    public void insertUser(User user) {
        userRepository.insertUser(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
}
