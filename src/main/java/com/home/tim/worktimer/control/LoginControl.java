package com.home.tim.worktimer.control;

import com.home.tim.worktimer.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginControl {
    @Autowired
    private UserControl userControl;

    public boolean isRegisteredUser(String userName, String password) {
        UserDTO user = userControl.getUserByUsernameAndPassword(userName, password);
        return user != null;
    }

}
