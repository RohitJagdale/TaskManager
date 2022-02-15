package com.taskmanager.horkrux.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.taskmanager.horkrux.Activites.SubmitTaskActivity;
import com.taskmanager.horkrux.Models.Task;
import com.taskmanager.horkrux.R;
import com.taskmanager.horkrux.databinding.TaskLayoutBinding;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    Context context;
    ArrayList<Task> tasks;

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_layout, parent, false);
        return new TaskAdapter.TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {

        holder.binding.userTaskTitle.setText(tasks.get(position).getTaskTitle());
        if (tasks.get(position).getTaskDescription().length() < 30) {
            holder.binding.userTaskDescription.setText(tasks.get(position).getTaskDescription());
        } else {

            String smg = tasks.get(position).getTaskDescription().substring(0, 30) + "... ";
            Toast.makeText(context, smg, Toast.LENGTH_SHORT).show();
            holder.binding.userTaskDescription.setText(smg);
        }


        holder.binding.taskItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SubmitTaskActivity.class);
                intent.putExtra("selectedTask", tasks.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });

    }


    public void applyFilter(ArrayList<Task> filteredTask) {
        tasks = filteredTask;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TaskLayoutBinding binding;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = binding.bind(itemView);


        }
    }
}
