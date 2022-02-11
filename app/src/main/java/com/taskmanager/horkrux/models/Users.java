package com.taskmanager.horkrux.models;

import java.io.Serializable;

public class Users implements Serializable {

    private  String userName, userEmail, userPass,fireuserid;

    public Users(String userEmail, String userPass, String userName,String fireuserid)
    {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPass = userPass;
        this.fireuserid = fireuserid;
    }

    public Users(){

    }


    public String getFireuserid() {
        return fireuserid;
    }

    public void setFireuserid(String fireuserid) {
        this.fireuserid = fireuserid;
    }


    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
