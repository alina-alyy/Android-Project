package com.example.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.Dao.Account_Dao;
import com.example.myapplication.model.Account;

@Database(entities = Account.class,exportSchema = false,version = 1)
public abstract class DatabaseClient extends RoomDatabase{
    private static String database_name="my_database";
    private static DatabaseClient instance;
    public static synchronized DatabaseClient getDB(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context,DatabaseClient.class,database_name).fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract Account_Dao account_dao();
}
