package com.home.tim.worktimer.repositories;

import com.home.tim.worktimer.entities.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface TimeStampRepository extends JpaRepository<Timestamp, Integer> {

    @Query("Select t FROM Timestamp t WHERE t.timestampid = ?1")
    Timestamp findById(int id);


}
