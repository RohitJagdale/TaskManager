package com.taskmanager.horkrux.Activites;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.taskmanager.horkrux.Authentication.LoginAndSignUp;
import com.taskmanager.horkrux.Models.Users;
import com.taskmanager.horkrux.databinding.ActivityProfileBinding;

public class Profile extends AppCompatActivity {

    ActivityProfileBinding binding;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private Context context = Profile.this;
    private String USER_PATH;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        initialize
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        USER_PATH = "Users/" + firebaseAuth.getUid() + "/";


//        Toast.makeText(this,MainActivity.count.getTodo()+"" , Toast.LENGTH_SHORT).show();
        binding.todoCount.setText(String.valueOf(MainActivity.count.getTodo()));
        binding.inProgressCount.setText(String.valueOf(MainActivity.count.getInProgress()));
        binding.doneCount.setText(String.valueOf(MainActivity.count.getDone()));

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        binding.userSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Do you really want to sign out");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                firebaseAuth.signOut();
                                startActivity(new Intent(getApplicationContext(), LoginAndSignUp.class));
                                finish();
                                dialog.cancel();
                            }
                        });
                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();


            }
        });

        database.getReference().child(USER_PATH).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                binding.profileName.setText(user.getUserName());
                binding.profileEmail.setText(user.getUserEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}