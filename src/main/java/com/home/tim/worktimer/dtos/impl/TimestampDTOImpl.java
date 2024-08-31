package com.home.tim.worktimer.dtos.impl;

import com.home.tim.worktimer.dtos.TimestampDTO;
import com.home.tim.worktimer.entities.Timestamp;
import com.home.tim.worktimer.entities.Type;

import java.time.Duration;
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
    public int getUserID() {
        return userID;
    }

    @Override
    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public Timestamp getTimeStamp() {
        return timestamp;
    }

    @Override
    public String timeDifferenceSinceStart() {
        Duration duration = Duration.between(this.getTime(),LocalDateTime.now());


        int secondsLong= (int) (duration.getSeconds()%60);
        int minutesLong= (int) (duration.toMinutes()%60);
        int hoursLong= (int) (duration.toHours()%24);

        String hours = addLeadingZero(hoursLong);
        String minutes = addLeadingZero(minutesLong);
        String seconds = addLeadingZero(secondsLong);

        return hours + ":" + minutes + ":" + seconds;
    }

    @Override
    public void setTimeStamp(Timestamp timeStamp) {
        this.timestamp = timeStamp;
    }

    @Override
    public String toString() {

        int hourInt = time.getHour();
        int minuteInt = time.getMinute();
        int secondsInt = time.getSecond();

        String hour = addLeadingZero(hourInt);
        String minute = addLeadingZero(minuteInt);
        String seconds = addLeadingZero(secondsInt);

        return hour + ":" + minute + ":" + seconds;
    }

    private String addLeadingZero(int time) {

        if (time < 10) {
            return "0" + time;
        }

        return time+"";

    }
}
