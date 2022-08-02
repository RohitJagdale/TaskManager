package com.taskmanager.horkrux;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.FirebaseDatabase;
import com.taskmanager.horkrux.Models.Task;
import com.taskmanager.horkrux.Models.Users;
import com.taskmanager.horkrux.Notification.ApiUtils;
import com.taskmanager.horkrux.Notification.NotificationData;
import com.taskmanager.horkrux.Notification.PushNotification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            Log.d("NOTI", "sendNotificationToUser: " + data.getNotificationTitle());

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    static public void sendNotificationToUser(ArrayList<Users> users, Context context, String title, String desc) {
        database = FirebaseDatabase.getInstance();
        for (Users user : users) {
            String topic = "/topics/" + user.getFireuserid();
            NotificationData data = new NotificationData();
            data.setNotificationTitle(title);
            data.setNotificationMessage(desc);
            data.setNotificationDate(getCurrentDateAndTime());
            PushNotification notification = new PushNotification(data, topic);
            Log.d("NOTI", "sendNotificationToUser: " + data.getNotificationTitle());
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    static public String getCurrentDateAndTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before Formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E,MMM dd yyyy HH:mm");

        String formattedDate = myDateObj.format(myFormatObj);

        return formattedDate;
    }

    static public String generateId() {
        return String.valueOf(new Date().getTime());
    }

    static public void pushData() {

    }
}
