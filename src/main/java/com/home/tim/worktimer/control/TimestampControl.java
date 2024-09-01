package com.home.tim.worktimer.control;

import com.home.tim.worktimer.dtos.TimestampDTO;
import com.home.tim.worktimer.dtos.UserDTO;
import com.home.tim.worktimer.dtos.impl.TimestampDTOImpl;
import com.home.tim.worktimer.entities.Timestamp;
import com.home.tim.worktimer.entities.Type;
import com.home.tim.worktimer.repositories.TimeStampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class TimestampControl {
    @Autowired
    private TimeStampRepository timeStampRepository;

    public TimestampDTO getLatestTimeOfUser(UserDTO userDTO){

        if (userDTO.getUser() == null)
            return null;

        List<Timestamp> allTimeStamps = timeStampRepository.findAllTimeStampsOfUser(userDTO.getUserID());
        Optional<Timestamp> optionalLatestDate = allTimeStamps.stream().max(Comparator.comparing(Timestamp::getTime));


        if (!optionalLatestDate.isPresent()){
            System.err.println("Could not get TimeStamp");
            return null;
        }
        Timestamp latestDate = optionalLatestDate.get();


        return convertToDTO(latestDate);
    }

    public TimestampDTO getLatestTypeTimeOfUser(UserDTO userDTO,Type type){

        if (userDTO.getUser() == null)
            return null;

        List<Timestamp> allTimeStamps = timeStampRepository.findAllTypeTimeStampsOfUser(userDTO.getUserID(),type);
        Optional<Timestamp> optionalLatestDate = allTimeStamps.stream().max(Comparator.comparing(Timestamp::getTime));


        if (!optionalLatestDate.isPresent()){
            System.err.println("Could not get TimeStamp");
            return null;
        }
        Timestamp latestDate = optionalLatestDate.get();


        return convertToDTO(latestDate);
    }

    public void saveTimeStamp(TimestampDTO timestampDTO){
        Timestamp timestamp = new Timestamp();

        timestamp.setTime(timestampDTO.getTime());
        timestamp.setType(timestampDTO.getType());
        timestamp.setUserid(timestampDTO.getUserID());

        timeStampRepository.save(timestamp);

    }

    public  void updateTimeStamp(TimestampDTO timestampDTO){
        Timestamp timestamp = new Timestamp();

        timestamp.setTime(timestampDTO.getTime());
        timestamp.setType(timestampDTO.getType());
        timestamp.setUserid(timestampDTO.getUserID());
        timestamp.setTimestampid(timestamp.getTimestampid());

        timeStampRepository.save(timestamp);

    }
    public void saveCurrentTimeStamp(UserDTO userDTO){
        TimestampDTO timestampDTO = new TimestampDTOImpl();

        timestampDTO.setTime(LocalDateTime.now());
        timestampDTO.setType(Type.END);
        timestampDTO.setUserID(userDTO.getUserID());

        saveTimeStamp(timestampDTO);

    }

    private TimestampDTO convertToDTO(Timestamp timestamp){
        TimestampDTO timestampDTO = new TimestampDTOImpl();

        timestampDTO.setTimeStamp(timestamp);
        timestampDTO.setTime(timestamp.getTime());
        timestampDTO.setUserID(timestamp.getUserid());
        timestampDTO.setType(timestamp.getType());

        return timestampDTO;
    }
}
