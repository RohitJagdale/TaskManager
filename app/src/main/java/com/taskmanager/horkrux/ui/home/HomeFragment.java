package com.taskmanager.horkrux.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.taskmanager.horkrux.Activites.AssignTaskActivity;
import com.taskmanager.horkrux.Adapters.TaskAdapter;
import com.taskmanager.horkrux.Models.Task;
import com.taskmanager.horkrux.R;
import com.taskmanager.horkrux.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    final String[] taskCategories = {"ALL", "TODO", "IN PROGRESS", "DONE"};
    final int ALL = 0, TODO = 1, IN_PROGRESS = 2, DONE = 3;
    ArrayAdapter taskCategoryAdapter;
    TaskAdapter taskAdapter;
    String currentUserId;
    ArrayList<Task> userTasks;
    ProgressDialog loader;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        taskCategoryAdapter = new ArrayAdapter(getContext(), R.layout.home_list_item, taskCategories);
        binding.taskCategory.setAdapter(taskCategoryAdapter);

//        currentUserId = FirebaseAuth.getInstance().getUid();
//        Toast.makeText(getContext(), FirebaseAuth.getInstance().getUid(), Toast.LENGTH_SHORT).show();
        loader = new ProgressDialog(getContext());
        loader.setMessage("Loading your tasks please wait !!!");
        loader.setCancelable(false);

        userTasks = new ArrayList<>();

        binding.taskCategory.setOnItemClickListener(taskCategoryListener);

        taskAdapter = new TaskAdapter(getContext(), userTasks);
        binding.userTaskRecylerView.setAdapter(taskAdapter);
        binding.userTaskRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadTask();


        binding.assignTaskButton.setOnClickListener(assignTaskBtnAction);


        return binding.getRoot();
    }

    View.OnClickListener assignTaskBtnAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getContext(), AssignTaskActivity.class));

        }
    };

    // loading user tasks
    void loadTask() {
        loader.show();
        FirebaseDatabase.getInstance().getReference().child("all-tasks")
                .child("user-tasks")
                .child(FirebaseAuth.getInstance().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        userTasks.clear();
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            Task task = snap.getValue(Task.class);
                            userTasks.add(task);
                        }

                        taskAdapter.notifyDataSetChanged();
                        loader.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }


    // for applying filter
    AdapterView.OnItemClickListener taskCategoryListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == ALL) {
                taskAdapter.applyFilter(userTasks);
            } else if (position == TODO) {
                ArrayList<Task> todoTasks = new ArrayList<>();
                for (Task task : userTasks) {
                    if (task.getTaskStatus().equals(Task.TODO)) {
                        todoTasks.add(task);
                    }
                }
                taskAdapter.applyFilter(todoTasks);
            } else if (position == IN_PROGRESS) {
                ArrayList<Task> inProgressTasks = new ArrayList<>();
                for (Task task : userTasks) {
                    if (task.getTaskStatus().equals(Task.IN_PROGRESS)) {
                        inProgressTasks.add(task);
                    }
                }
                taskAdapter.applyFilter(inProgressTasks);
            } else {
                ArrayList<Task> doneTasks = new ArrayList<>();
                for (Task task : userTasks) {
                    if (task.getTaskStatus().equals(Task.DONE)) {
                        doneTasks.add(task);
                    }
                }
                taskAdapter.applyFilter(doneTasks);
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}