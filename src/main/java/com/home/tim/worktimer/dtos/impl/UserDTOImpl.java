package com.home.tim.worktimer.dtos.impl;

import com.home.tim.worktimer.dtos.UserDTO;
import lombok.Getter;
import lombok.Setter;


public class UserDTOImpl implements UserDTO {

    private int userid;
    private String username;
    private String email;
    private String password;
    private String role;


    @Override
    public int getUserID() {
        return userid;
    }

    @Override
    public void setUSerID(int userid) {
        this.userid=userid;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public void setUserName(String username) {
        this.username=username;
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
