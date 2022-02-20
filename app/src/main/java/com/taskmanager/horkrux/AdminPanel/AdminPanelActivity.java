package com.taskmanager.horkrux.AdminPanel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.taskmanager.horkrux.Models.Users;
import com.taskmanager.horkrux.databinding.ActivityAdminPanelBinding;

public class AdminPanelActivity extends AppCompatActivity {

    private ActivityAdminPanelBinding binding;
    private final Context context = AdminPanelActivity.this;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminPanelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.teamAndMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                binding.OuterLayout.setVisibility(View.GONE);
                binding.InnerLayout.setVisibility(View.VISIBLE);
            }
        });

        binding.teamAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teamAndroidIntent = new Intent(context, TeamMemberList.class);
                teamAndroidIntent.putExtra("requestedTeam", Users.ANDROID_DEPT);
                startActivity(teamAndroidIntent);
            }
        });

        binding.teamWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teamAndroidIntent = new Intent(context, TeamMemberList.class);
                teamAndroidIntent.putExtra("requestedTeam", Users.WEB_DEPT);
                startActivity(teamAndroidIntent);
            }
        });

        binding.teamUiUx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teamAndroidIntent = new Intent(context, TeamMemberList.class);
                teamAndroidIntent.putExtra("requestedTeam", Users.UI_UX_DEPT);
                startActivity(teamAndroidIntent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (count == 1) {
            binding.OuterLayout.setVisibility(View.VISIBLE);
            binding.InnerLayout.setVisibility(View.GONE);
            count--;
        } else {
            count--;
            super.onBackPressed();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        binding.OuterLayout.setVisibility(View.VISIBLE);
        binding.InnerLayout.setVisibility(View.GONE);
        return super.onSupportNavigateUp();
    }
}