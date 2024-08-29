package com.home.tim.worktimer.dtos.impl;

import com.home.tim.worktimer.dtos.TimestampDTO;
import com.home.tim.worktimer.entities.Timestamp;
import com.home.tim.worktimer.entities.Type;

import java.time.LocalDateTime;

public class TimestampDTOImpl implements TimestampDTO {
    private int userID;
    private Type type;
    private LocalDateTime time;
    private Timestamp timestamp;

    @Override
    public int getTimeStampID() {
        return timestamp.getTimestampid();
    }

    @Override
    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public int getUserID() {
        return userID;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public void setTimeStamp(Timestamp timeStamp) {
        this.timestamp = timeStamp;
    }

    @Override
    public Timestamp getTimeStamp() {
        return timestamp;
    }


    @Override
    public String toString(){

        int hour = time.getHour();
        int minute = time.getMinute();
        int seconds = time.getSecond();

        return hour+":"+minute+":"+seconds;
    }
}
