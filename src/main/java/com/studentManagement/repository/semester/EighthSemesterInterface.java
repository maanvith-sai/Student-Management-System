package com.studentManagement.repository.semester;

import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.eighth;
import com.studentManagement.entity.semester.first;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface EighthSemesterInterface extends JpaRepository<eighth,Long> {
    @Query("SELECT s FROM Student s JOIN FETCH s.eighth")
    List<Student> findAllUsersWithAddresseight();



    @Query("SELECT s FROM eighth s where s.id1=:value")
    List<eighth> findUsersWithId(@Param("value") String value);
}
