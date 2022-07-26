package com.taskmanager.horkrux.Notification;

public class NotificationData {
    private String notificationTitle;
    private String notificationMessage;
    private String notificationDate;

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }

    private String notificationTime;


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
