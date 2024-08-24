package com.home.tim.worktimer.repositories;

import com.home.tim.worktimer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoery extends JpaRepository <User, Long> {

    User save(User user);
}
