package com.taskmanager.horkrux.Models;

import java.util.Date;

public class Task {
    protected String taskID, taskTitle, taskDescription, taskAssigned, taskDeadline, taskAssignedTo, taskAssignedFrom, taskStatus;
    protected String[] grpTask;
    protected boolean isSeen = false;
    protected Date taskAssignedDate;
    protected Date taskDeadlineDate;

    public Task() {
    }

    public Task(String taskID, String taskTitle, String taskDescription, String taskAssigned, String taskDeadline, String taskAssignedTo, String taskAssignedFrom, String taskStatus, String[] grpTask, boolean isSeen, Date taskAssignedDate, Date taskDeadlineDate) {
        this.taskID = taskID;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskAssigned = taskAssigned;
        this.taskDeadline = taskDeadline;
        this.taskAssignedTo = taskAssignedTo;
        this.taskAssignedFrom = taskAssignedFrom;
        this.taskStatus = taskStatus;
        this.grpTask = grpTask;
        this.isSeen = isSeen;
        this.taskAssignedDate = taskAssignedDate;
        this.taskDeadlineDate = taskDeadlineDate;
    }


    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskAssigned() {
        return taskAssigned;
    }

    public void setTaskAssigned(String taskAssigned) {
        this.taskAssigned = taskAssigned;
    }

    public String getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(String taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public String getTaskAssignedTo() {
        return taskAssignedTo;
    }

    public void setTaskAssignedTo(String taskAssignedTo) {
        this.taskAssignedTo = taskAssignedTo;
    }

    public String getTaskAssignedFrom() {
        return taskAssignedFrom;
    }

    public void setTaskAssignedFrom(String taskAssignedFrom) {
        this.taskAssignedFrom = taskAssignedFrom;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String[] getGrpTask() {
        return grpTask;
    }

    public void setGrpTask(String[] grpTask) {
        this.grpTask = grpTask;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public Date getTaskAssignedDate() {
        return taskAssignedDate;
    }

    public void setTaskAssignedDate(Date taskAssignedDate) {
        this.taskAssignedDate = taskAssignedDate;
    }

    public Date getTaskDeadlineDate() {
        return taskDeadlineDate;
    }

    public void setTaskDeadlineDate(Date taskDeadlineDate) {
        this.taskDeadlineDate = taskDeadlineDate;
    }
}
