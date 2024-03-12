package com.studentManagement.repository;


import com.studentManagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("SELECT u FROM Student u WHERE u.id = ?1")
    Student findByUserId(String userId);
}
