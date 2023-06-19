package com.example.myapplication.Dao;

import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.model.Account;

@Dao
public interface Account_Dao {
    @Query("Select * from accounts WHERE username = :username AND password = :password")
    Account getAccount(String username, String password);
    @Insert
    void register(Account account);

}
