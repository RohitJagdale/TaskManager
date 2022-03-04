package com.taskmanager.horkrux.Notification;

public class NotificationData {
    private String notificationTitle, notificationMessage;


    public NotificationData() {
    }

    public NotificationData(String notificationTitle, String notificationMessage) {
        this.notificationTitle = notificationTitle;
        this.notificationMessage = notificationMessage;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }
}
