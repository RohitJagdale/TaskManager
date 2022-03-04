package com.taskmanager.horkrux.Activites;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.taskmanager.horkrux.Authentication.LoginAndSignUp;
import com.taskmanager.horkrux.Models.Users;
import com.taskmanager.horkrux.databinding.ActivityProfileBinding;

public class Profile extends AppCompatActivity {

    private ActivityProfileBinding binding;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private Context context = Profile.this;
    private String USER_PATH;
    private String m_Text;


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

        binding.editUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Enter User Name");


                final EditText input = new EditText(context);

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        if (m_Text.isEmpty()) {
                            Toast.makeText(context, "Username cant be empty!!", Toast.LENGTH_SHORT).show();
                        } else {
                            database.getReference(USER_PATH).child("userName").setValue(m_Text).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(context, "Username updated successfully", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });


        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 45);
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (data.getData() != null) {
                Uri uri;
                uri = data.getData();
                binding.profileImage.setImageURI(uri);
//                selectedImage = data.getData();
//                binding.UploadProfile.setVisibility(View.GONE);
//                binding.SaveProfile.setVisibility(View.VISIBLE);
//                Cursor returnCursor = getContentResolver().query(uri, null, null, null, null);
////                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
//                int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
//                returnCursor.moveToFirst();
////                long imageSize = returnCursor.getLong(sizeIndex) / 1000;
//                if (imageSize > 200) {
//                    Toast.makeText(SelfUserProfileActivity.this, "Please select image whose size is less than 200KB", Toast.LENGTH_SHORT).show();
//                    return;
//                } else {

//                }
            }
        }
    }
}