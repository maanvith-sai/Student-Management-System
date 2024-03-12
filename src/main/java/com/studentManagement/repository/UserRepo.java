package com.studentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentManagement.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {


    public User findByUserId(String userId);


    @Modifying
    @Query("DELETE FROM User u WHERE u.userId = :userId")
    void deleteByUserId(String userId);

}
