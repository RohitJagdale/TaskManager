package com.taskmanager.horkrux;

import android.content.Context;
import android.widget.Toast;

import com.taskmanager.horkrux.Models.Task;
import com.taskmanager.horkrux.Models.Users;
import com.taskmanager.horkrux.Notification.ApiUtils;
import com.taskmanager.horkrux.Notification.NotificationData;
import com.taskmanager.horkrux.Notification.PushNotification;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonUtils {
    static public void sendNotificationToUser(Task task, Context context) {
        NotificationData data = new NotificationData();
        data.setTitle(task.getTaskTitle());
        data.setMessage(task.getTaskDescription());

        for (Users user : task.getGrpTask()) {
            PushNotification notification = new PushNotification(data, user.getFireuserid());

            ApiUtils.getClient().sendNotification(notification).enqueue(new Callback<PushNotification>() {
                @Override
                public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PushNotification> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }


    }
}
