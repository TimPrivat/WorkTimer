package com.home.tim.worktimer.control;

import com.home.tim.worktimer.dtos.UserDTO;
import com.home.tim.worktimer.dtos.impl.UserDTOImpl;
import com.home.tim.worktimer.entities.User;
import com.vaadin.flow.component.AttachEvent;
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

    public UserDTO validateUser(AttachEvent attachEvent) {
        UserDTO currentUser = (UserDTO) attachEvent.getSession().getAttribute("CurrentUser");

        if (currentUser == null || !isRegisteredUser(currentUser.getUserName(), currentUser.getPassword())) {
            attachEvent.getUI().getCurrent().navigate("login");
            return new UserDTOImpl();
        }

        return currentUser;

    }

}
