package com.home.tim.worktimer.dtos;

import com.home.tim.worktimer.entities.Timestamp;
import com.home.tim.worktimer.entities.Type;

import java.time.LocalDateTime;

public interface TimestampDTO {


    int getTimeStampID();
    void setUserID(int userID);
    int getUserID();

    void setType (Type type);
    Type getType();
    void setTime(LocalDateTime time);
    LocalDateTime getTime();

    String timeDifferenceSinceStart();
    String timeDifferenceBetween(LocalDateTime localDateTime);

    void setTimeStamp(Timestamp timeStamp);
    Timestamp getTimeStamp();

    String toString();
}
