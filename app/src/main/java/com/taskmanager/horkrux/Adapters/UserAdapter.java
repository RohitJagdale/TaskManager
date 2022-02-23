package com.taskmanager.horkrux.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;
import com.taskmanager.horkrux.Activites.AssignTaskActivity;
import com.taskmanager.horkrux.Models.Users;
import com.taskmanager.horkrux.R;
import com.taskmanager.horkrux.databinding.UserItemBinding;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersViewHolder> {
    Context context;
    ArrayList<Users> users;
    ArrayList<Users> backUsers;
    String from;


    public UserAdapter(Context context, ArrayList<Users> users, String from) {
        this.context = context;
        this.users = users;
        this.backUsers = users;
        this.from = from;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {

        holder.binding.userName.setText(users.get(position).getUserName());

        holder.binding.clearTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(holder.getAdapterPosition());
            }
        });
    }

    public void reset() {
    }

    public void removeItem(int poi) {
        if (from != null) {

//            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
            FirebaseDatabase.getInstance().getReference().child("all-tasks/user-tasks").child(users.get(poi).getFireuserid()).child(from).setValue(null);
        }

        AssignTaskActivity.showingItems.add(users.get(poi).getUserName());

        AssignTaskActivity.items.add(users.remove(poi));


        notifyItemRemoved(poi);
        notifyItemRangeChanged(poi, users.size());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        UserItemBinding binding;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = binding.bind(itemView);


        }
    }
}

