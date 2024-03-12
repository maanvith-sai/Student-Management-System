package com.studentManagement.repository;

import com.studentManagement.entity.Student;
import com.studentManagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
    @Query("SELECT u FROM Teacher u WHERE u.teacherId = ?1")
    Teacher findByTeacherId(String userId);
}
