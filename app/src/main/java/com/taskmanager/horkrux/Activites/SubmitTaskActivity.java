package com.taskmanager.horkrux.Activites;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.taskmanager.horkrux.Models.Task;
import com.taskmanager.horkrux.databinding.ActivitySubmitTaskBinding;

public class SubmitTaskActivity extends AppCompatActivity {

    final Context context = SubmitTaskActivity.this;
    ActivitySubmitTaskBinding binding;
    Task selectedTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySubmitTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        selectedTask = (Task) getIntent().getSerializableExtra("selectedTask");

        Toast.makeText(context, selectedTask.getTaskID(), Toast.LENGTH_SHORT).show();


    }
}