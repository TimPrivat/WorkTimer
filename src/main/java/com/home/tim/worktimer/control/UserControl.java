package com.home.tim.worktimer.control;

import com.home.tim.worktimer.dtos.UserDTO;
import com.home.tim.worktimer.dtos.impl.UserDTOImpl;
import com.home.tim.worktimer.entities.User;
import com.home.tim.worktimer.repositories.UserRepository;
import com.sun.jna.platform.win32.Netapi32Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class UserControl {

    @Autowired
    UserRepository userRepository;


    public void updateUser(User user) {
        userRepository.save(user);
    }


    public boolean userExists(String userName) {
        User user = userRepository.getUserByUsername(userName);
        return user != null;

    }


    public User getUserByPassword(String password) {
        User user = userRepository.getUserByPassword(password);
        if (user == null) {
            System.err.println("User couldn't be found.");
            ;
        }

        return user;

    }

    public UserDTO getUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.getUserByUsernameAndPassword(username, password);
        if (user == null) {
            System.err.println("User couldn't be found.");
        }

        return convertUserToDTO(user);

    }

    public void saveUser(UserDTO userDTO){

        User user = userDTO.getUser();

        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUserName());
        user.setRole(userDTO.getRole());
        user.setPassword(userDTO.getPassword());

        userRepository.save(user);

    }

    private UserDTO convertUserToDTO(User user){
        UserDTO userDTO = new UserDTOImpl();
        userDTO.setUserName(user.getUsername());
        userDTO.setEmail(userDTO.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        userDTO.setUser(user);

        return userDTO;

    }


}
