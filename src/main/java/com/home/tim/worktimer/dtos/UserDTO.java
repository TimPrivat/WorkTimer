package com.home.tim.worktimer.dtos;

import com.home.tim.worktimer.entities.User;

public interface UserDTO {

    int getUserID();

    String getUserName();

    void setUserName(String userName);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    String getRole();

    void setRole(String role);
    User getUser();
    void setUser(User user);



}
