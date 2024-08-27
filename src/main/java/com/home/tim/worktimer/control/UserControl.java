package com.home.tim.worktimer.control;

import com.home.tim.worktimer.entities.User;
import com.home.tim.worktimer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class UserControl {

    @Autowired
    UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

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
            throw new UsernameNotFoundException("User couldn't be found.");
        }

        return user;

    }



}
