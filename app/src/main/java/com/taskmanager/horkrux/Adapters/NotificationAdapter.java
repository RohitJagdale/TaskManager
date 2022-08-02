package com.taskmanager.horkrux.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.taskmanager.horkrux.Notification.NotificationData;
import com.taskmanager.horkrux.R;
import com.taskmanager.horkrux.databinding.NotificationLayoutBinding;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private final Context context;
    private final ArrayList<NotificationData> notificationData;

    public NotificationAdapter(Context context, ArrayList<NotificationData> notificationData) {
        this.context = context;
        this.notificationData = notificationData;

    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_layout, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {

        String title = notificationData.get(holder.getAdapterPosition()).getNotificationTitle();
        String msg = notificationData.get(holder.getAdapterPosition()).getNotificationMessage();
        String timeAndDate = notificationData.get(holder.getAdapterPosition()).getNotificationDate();

        holder.binding.notificationTitle.setText(title);
        holder.binding.notificationDesc.setText(msg);
        holder.binding.notificationDate.setText(timeAndDate);


        holder.binding.taskItem.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(msg)
                    .show();
        });

    }

    public void reset() {
    }


    @Override
    public int getItemCount() {
        return notificationData.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {

        private NotificationLayoutBinding binding;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = binding.bind(itemView);


        }
    }
}

