package com.taskmanager.horkrux.AuthNew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.taskmanager.horkrux.Models.Users;
import com.taskmanager.horkrux.R;
import com.taskmanager.horkrux.databinding.ActivityNewSignUpBinding;

public class NewSignUp extends AppCompatActivity {

    private ActivityNewSignUpBinding binding;

    final String[] taskCategories = {"Android Dev", "Web Dev", "UI/UX", "MBA"};
    final int Android_Dev = 0, Web_Dev = 1, UI_UX = 2, MBA = 3;

    private ArrayAdapter fieldCategoryAdapter;

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    Users user = new Users();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //firebase declarations
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


//        load field
        fieldCategoryAdapter = new ArrayAdapter(getApplicationContext(), R.layout.home_list_item, taskCategories);
        binding.createUserField.setAdapter(fieldCategoryAdapter);


        binding.createUserLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = binding.createUserEmail.getText().toString().trim();
                String password = binding.createUserPass.getText().toString().trim();
                String username = binding.crateUserName.getText().toString().trim();

                if(email.isEmpty() || password.isEmpty() || username.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Fill All Fields",Toast.LENGTH_SHORT).show();
                }
                else if (password.length() < 7) {

                    Toast.makeText(getApplicationContext(),"Must be 8 characters or more",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    user.setUserEmail(binding.createUserEmail.getText().toString().trim());
                    user.setUserPass(binding.createUserPass.getText().toString().trim());
                    user.setUserName(binding.crateUserName.getText().toString().trim());

                    //register user
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Resgistration Succesfull",Toast.LENGTH_SHORT).show();
                                sendEmailVerification();
                                user.setFireuserid(firebaseAuth.getUid());
                                updateUser(user);

                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Resgistration Unsuccesfull",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });

    }


    //send verification email
    private void sendEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(),"Verification e-mail Sent",Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();

                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(),"Failed to send verification e-mail",Toast.LENGTH_SHORT).show();
        }
    }

    //  update the user in database
    private void updateUser(Users username) {
        firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).setValue(username).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"user is in database",Toast.LENGTH_SHORT).show();
                    Log.d("result", "onComplete: " + task.getException());
                }
                else{
                    Toast.makeText(getApplicationContext(),"user is not add in database",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}