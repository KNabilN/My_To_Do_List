package com.fitness.android.myapplication.POJO;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tasks")
public class Tasks {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    @ColumnInfo(name = "task")
    private String des;
    private int importance;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDes() {
        return des;
    }

    public int getImportance() {
        return importance;
    }

    public Tasks(int id, String title, String des, int importance) {
        this.id = id;
        this.title = title;
        this.des = des;
        this.importance = importance;
    }

    @Ignore
    public Tasks(String title, String des, int importance) {
        this.title = title;
        this.des = des;
        this.importance = importance;
    }
}
