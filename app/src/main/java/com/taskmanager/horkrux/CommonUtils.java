package com.taskmanager.horkrux;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.taskmanager.horkrux.Models.Task;
import com.taskmanager.horkrux.Models.Users;
import com.taskmanager.horkrux.Notification.ApiUtils;
import com.taskmanager.horkrux.Notification.NotificationData;
import com.taskmanager.horkrux.Notification.PushNotification;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonUtils {
    private static FirebaseDatabase database;
    private static final String PATH = "Notifications";

    static public void sendNotificationToUser(Task task, Context context) {
        database = FirebaseDatabase.getInstance();
        for (Users user : task.getGrpTask()) {
            String topic = "/topics/" + user.getFireuserid();
            NotificationData data = new NotificationData();
            data.setNotificationTitle(task.getTaskTitle());
            data.setNotificationMessage(task.getTaskDescription());
            PushNotification notification = new PushNotification(data, topic);

            ApiUtils.getClient().sendNotification(notification).enqueue(new Callback<PushNotification>() {
                @Override
                public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
                    if (response.isSuccessful()) {
//                        Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                        database.getReference().child(PATH).child(user.getFireuserid()).child(generateId()).setValue(notification.getData());
                    } else {
//                        Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PushNotification> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }


    }

    static public void sendNotificationToUser(ArrayList<Users> users, Context context, String title, String desc) {
        database = FirebaseDatabase.getInstance();
        for (Users user : users) {
            String topic = "/topics/" + user.getFireuserid();
            NotificationData data = new NotificationData();
            data.setNotificationTitle(title);
            data.setNotificationMessage(desc);
            PushNotification notification = new PushNotification(data, topic);

            ApiUtils.getClient().sendNotification(notification).enqueue(new Callback<PushNotification>() {
                @Override
                public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
                    if (response.isSuccessful()) {
//                        Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                        database.getReference().child(PATH).child(user.getFireuserid()).child(generateId()).setValue(notification.getData());

                    } else {
//                        Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PushNotification> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }


    }

    static public String generateId() {
        return String.valueOf(new Date().getTime());
    }

    static public void pushData() {

    }
}
