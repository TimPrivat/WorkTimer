package com.home.tim.worktimer.dtos.impl;

import com.home.tim.worktimer.dtos.UserDTO;
import lombok.Getter;
import lombok.Setter;


public class UserDTOImpl implements UserDTO {

    private int userID;
    private String userName;
    private String email;
    private String password;
    private String role;


    @Override
    public int getUserID() {
        return userID;
    }

    @Override
    public void setUSerID(int userID) {
        this.userID=userID;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName=userName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email=email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password=password;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role=role;
    }
}
