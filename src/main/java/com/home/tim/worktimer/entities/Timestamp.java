package com.home.tim.worktimer.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timestampid;
    private int userid;
    private Type type;
    private LocalDateTime time;

}
