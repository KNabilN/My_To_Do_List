package com.fitness.android.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fitness.android.myapplication.DataBase.AppDataBase;
import com.fitness.android.myapplication.POJO.Tasks;
import com.fitness.android.myapplication.R;

public class TasksActivity extends AppCompatActivity {

    private EditText title, task;
    private Button saveBtn;
    private int radioRes, id;

    private RadioButton exm, vr, imp;

    private String tit, des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        saveBtn = findViewById(R.id.save_btn);
        title = findViewById(R.id.edt_title);
        task = findViewById(R.id.edt_task);

        exm = findViewById(R.id.rbtn_extr);
        vr = findViewById(R.id.rbtn_very);
        imp = findViewById(R.id.rbtn_imp);

        setUI();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getDate();
            }
        });
    }

    private void getDate()
    {
        tit = title.getText().toString().trim();
        des = task.getText().toString().trim();
        int importance = getImportance();

        if (tit.isEmpty() || des.isEmpty() || importance == 0)
        {
            Toast.makeText(this, "Please, Check your task data...", Toast.LENGTH_SHORT).show();
        }
        else {
            saveTask(tit,des,importance);
        }
    }

    private void saveTask(String tit, String des, int importance) {
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        AppDataBase.getInstance(this.getApplicationContext())
                .doActoin().updateTask(tit, des, importance, id);
        finish();
    }

    private int getImportance()
    {
        RadioGroup rg = findViewById(R.id.radioGroup);
        int idR = rg.getCheckedRadioButtonId();

        switch (idR)
        {
            case R.id.rbtn_extr:
                return 1;
            case R.id.rbtn_very:
                return 2;
            case R.id.rbtn_imp:
                return 3;
            default:
                return 0;
        }
    }

    private void setUI()
    {
        if (getIntent() != null) {
            tit = getIntent().getStringExtra(TasksAdapter.TITLE);
            des = getIntent().getStringExtra(TasksAdapter.DES);
            radioRes = getIntent().getIntExtra(TasksAdapter.IMPORTANCE, 3);
            id = getIntent().getIntExtra(TasksAdapter.ID, 0);
        }

        title.setText(tit);
        task.setText(des);
        setRadio(radioRes);
    }

    private void setRadio(int res)
    {
        switch (res){
            case 1:
                exm.setChecked(true);
                break;
            case 2:
                vr.setChecked(true);
                break;
            case 3:
                imp.setChecked(true);
                break;
        }
    }
}
