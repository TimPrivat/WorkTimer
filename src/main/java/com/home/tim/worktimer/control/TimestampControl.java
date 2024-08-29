package com.home.tim.worktimer.control;

import com.home.tim.worktimer.dtos.UserDTO;
import com.home.tim.worktimer.entities.Timestamp;
import com.home.tim.worktimer.repositories.TimeStampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimestampControl {
    @Autowired
    private TimeStampRepository timeStampRepository;

    public Timestamp getLatestTimeOfUser(UserDTO userDTO){

        return null;
    }
}
