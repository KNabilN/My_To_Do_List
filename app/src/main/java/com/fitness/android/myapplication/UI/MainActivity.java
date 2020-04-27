package com.fitness.android.myapplication.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fitness.android.myapplication.DataBase.AppDataBase;
import com.fitness.android.myapplication.DataBase.AppDataBase_Impl;
import com.fitness.android.myapplication.POJO.Tasks;
import com.fitness.android.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private TasksAdapter adapter;
    private TasksViewModel tasksViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.tasks_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TasksAdapter(this);

        tasksViewModel = ViewModelProviders.of(this).get(TasksViewModel.class);

        //start observation
        tasksViewModel.getAll(this).observe(this, new Observer<List<Tasks>>() {
            @Override
            public void onChanged(List<Tasks> tasks) {
                Collections.reverse(tasks);
                adapter.setArrayList(tasks);
                recyclerView.setAdapter(adapter);
            }
        });


        // swipe
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
        {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                //delete
                AppDataBase.getInstance(MainActivity.this.getApplicationContext())
                        .doActoin().deleteTask(adapter.getTasks().get(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(recyclerView);


        FloatingActionButton fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //add new task
                Tasks tasks = new Tasks("New Task", "", 3);
                AppDataBase.getInstance(view.getContext().getApplicationContext()).doActoin().insertTask(tasks);
            }
        });
    }
}
