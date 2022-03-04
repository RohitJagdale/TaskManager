package com.taskmanager.horkrux.AuthNew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.taskmanager.horkrux.Activites.MainActivity;
import com.taskmanager.horkrux.AdminPanel.AdminPanelActivity;
import com.taskmanager.horkrux.databinding.ActivityNewLoginBinding;

public class NewLoginActivity extends AppCompatActivity {

    private ActivityNewLoginBinding binding;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Initialization
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

//        login user

        //        if user is already logged in
        if (firebaseUser != null) {
            startActivity(new Intent(getApplicationContext(), AdminPanelActivity.class));
            finish();
        }

        //        if user is not looged in

        binding.loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = binding.loginEmail.getText().toString().trim();
                String password = binding.loginPassword.getText().toString().trim();

                if(mail.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "All fields are required to fill",Toast.LENGTH_SHORT).show();
                }else if (password.length() < 7) {

                    Toast.makeText(getApplicationContext(),"Password must be 8 characters",Toast.LENGTH_SHORT).show();

                }
                else
                {

                    firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                checkMailVerification();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"User does not exists",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });


    }

    private void checkMailVerification()
    {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser.isEmailVerified() == true)
        {
            Toast.makeText(getApplicationContext(),"Logged in",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), AdminPanelActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"User Not verified",Toast.LENGTH_SHORT).show();
        }
    }

}