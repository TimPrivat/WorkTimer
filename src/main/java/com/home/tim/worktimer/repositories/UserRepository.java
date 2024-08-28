package com.home.tim.worktimer.repositories;

import com.home.tim.worktimer.dtos.UserDTO;
import com.home.tim.worktimer.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Component("userRepository")
public interface UserRepository extends JpaRepository <User, Integer> {

    User save(User user);

    @Query("DELETE FROM User u WHERE u.username = ?1")
    void deleteUserByUsername(String userName);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    UserDTO getUserByUsername(String userName);

    @Query("SELECT u FROM User u WHERE u.password = ?1")
    UserDTO getUserByPassword(String password);

    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
    UserDTO getUserByUsernameAndPassword(String userName, String password);

}
