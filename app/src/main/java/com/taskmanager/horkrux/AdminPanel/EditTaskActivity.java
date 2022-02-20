package com.taskmanager.horkrux.AdminPanel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.taskmanager.horkrux.databinding.ActivityEditTaskBinding;

public class EditTaskActivity extends AppCompatActivity {

    private ActivityEditTaskBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}