package com.myproject.notesappnvvm.Model.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.myproject.notesappnvvm.Model.Beans.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAllUsers();

    @Insert
    void insertUsers(User... user);

    @Query("DELETE FROM user_table WHERE id=:id")
    void deleteUser(int id);

    @Update
    void updateUser(User user);

    @Query("SELECT EXISTS (SELECT * FROM user_table WHERE username=:username)")
    boolean is_taken(String username);

    @Query("SELECT EXISTS(SELECT * FROM user_table WHERE username=:username AND password=:password)")
    boolean login(String username, String password);

}
