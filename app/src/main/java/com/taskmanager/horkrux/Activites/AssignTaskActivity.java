package com.taskmanager.horkrux.Activites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.taskmanager.horkrux.Adapters.UserAdapter;
import com.taskmanager.horkrux.Models.Task;
import com.taskmanager.horkrux.R;
import com.taskmanager.horkrux.databinding.ActivityAssignTaskBinding;

import java.util.ArrayList;
import java.util.Objects;

public class AssignTaskActivity extends AppCompatActivity {


    final Context context = AssignTaskActivity.this;
    private ActivityAssignTaskBinding binding;
    private Task task;
    private FirebaseDatabase database;
    private FirebaseFirestore firebaseFirestore;

    MaterialDatePicker.Builder materialDateBuilder;
    MaterialDatePicker startDatePicker;
    MaterialDatePicker dueDatePicker;
    ListPopupWindow userList;
    UserAdapter adapter;
    ArrayAdapter userListAdapter;
    ArrayList<String> assignedList;
    ArrayList<String> items;

    @SuppressLint("NotifyDataSetChanged")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAssignTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();



        materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("SELECT A DATE");
        startDatePicker = materialDateBuilder.build();
        dueDatePicker = materialDateBuilder.build();
        assignedList = new ArrayList<>();
        items = new ArrayList<>();
        userList = new ListPopupWindow(context);
        adapter = new UserAdapter(AssignTaskActivity.this, assignedList);


        //date picker
        binding.startDate.setOnClickListener(startDatePick);
        binding.dueDate.setOnClickListener(dueDatePick);
        dueDatePicker.addOnPositiveButtonClickListener(dueDateOnPositive);
        startDatePicker.addOnPositiveButtonClickListener(startDateOnPositive);


        //action on add button
        binding.assignTaskToUserBtn.setOnClickListener(assignUserToTask);

        binding.taskAssignTo.setLayoutManager(new GridLayoutManager(AssignTaskActivity.this, 2));
        binding.taskAssignTo.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        binding.submitTask.setOnClickListener(assignTask);


        //sample items
        items.add("Musheerxcscssc");
        items.add("jaggya");
        items.add("kallya");
        items.add("iqbal");
        items.add("jadu");
        items.add("adu");
        items.add("sai");
        items.add("tatti");
        items.add("soma");
        items.add("pintya");
        items.add("gobya");

    }


    View.OnClickListener assignTask = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (binding.taskTitle.getText().toString().isEmpty() || binding.taskDescription.getText().toString().isEmpty()) {
                Toast.makeText(context, "Please fill all details", Toast.LENGTH_SHORT).show();
                return;

            }

            if (binding.priorityGroup.getCheckedChipId() == -1) {
                Toast.makeText(context, "Please select priority", Toast.LENGTH_SHORT).show();
                return;
            }

            if (assignedList.isEmpty()) {
                Toast.makeText(context, "Please Assign task to someone", Toast.LENGTH_SHORT).show();
                return;
            }

            if (binding.startDate.getText().toString().equals("Pick Start Date ....")) {
                Toast.makeText(context, "Please Pick start date", Toast.LENGTH_SHORT).show();
                return;

            }

            if (binding.dueDate.getText().toString().equals("Pick Due Date ....")) {
                Toast.makeText(context, "Please Pick a due date", Toast.LENGTH_SHORT).show();
                return;

            }

            task = new Task();


        }
    };

    View.OnClickListener assignUserToTask = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            userListAdapter = new ArrayAdapter(context, R.layout.user_list, items);
            userList.setHeight(ListPopupWindow.WRAP_CONTENT);
            userList.setWidth(600);
            userList.setAnchorView(v);
            userList.setAdapter(userListAdapter);

            userList.setOnItemClickListener(userClicked);
            userList.show();

        }
    };

    AdapterView.OnItemClickListener userClicked = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            assignedList.add(items.get(position));
            items.remove(position);
            userList.dismiss();
            adapter.notifyDataSetChanged();
        }
    };

    View.OnClickListener startDatePick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        }
    };


    View.OnClickListener dueDatePick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dueDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        }
    };

    MaterialPickerOnPositiveButtonClickListener startDateOnPositive = new MaterialPickerOnPositiveButtonClickListener() {
        @Override
        public void onPositiveButtonClick(Object selection) {
            binding.startDate.setText("Start Date is, " + startDatePicker.getHeaderText());
        }
    };


    MaterialPickerOnPositiveButtonClickListener dueDateOnPositive = new MaterialPickerOnPositiveButtonClickListener() {
        @Override
        public void onPositiveButtonClick(Object selection) {
            binding.dueDate.setText("Due Date is, " + dueDatePicker.getHeaderText());
        }
    };


}