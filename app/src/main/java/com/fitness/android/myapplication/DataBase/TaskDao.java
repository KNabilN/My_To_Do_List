package com.fitness.android.myapplication.DataBase;

import com.fitness.android.myapplication.POJO.Tasks;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TaskDao
{
    @Query("SELECT * FROM tasks")
    LiveData<List<Tasks>> getAll();

    @Insert
    void insertTask(Tasks task);

    @Query("UPDATE tasks SET title = :title, task = :des, importance = :importance WHERE id = :id")
    void updateTask(String title, String des, int importance, int id);

    @Delete
    void deleteTask(Tasks task);


}
