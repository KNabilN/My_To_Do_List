package com.fitness.android.myapplication.UI;

import android.content.Context;
import android.widget.Toast;

import com.fitness.android.myapplication.DataBase.AppDataBase;
import com.fitness.android.myapplication.POJO.Tasks;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class TasksViewModel extends ViewModel
{
   private LiveData<List<Tasks>> tasks;


    public LiveData<List<Tasks>> getAll(Context context)
    {
        tasks = AppDataBase.getInstance(context.getApplicationContext()).doActoin().getAll();
        Toast.makeText(context, "HH", Toast.LENGTH_SHORT).show();
        return tasks;
    }

}
