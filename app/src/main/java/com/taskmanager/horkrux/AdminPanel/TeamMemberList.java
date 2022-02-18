package com.taskmanager.horkrux.AdminPanel;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.taskmanager.horkrux.databinding.ActivityTeamMemberListBinding;

public class TeamMemberList extends AppCompatActivity {

    ActivityTeamMemberListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTeamMemberListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String requestedTeam = getIntent().getStringExtra("requestedTeam");

        Toast.makeText(this, requestedTeam, Toast.LENGTH_SHORT).show();
    }
}