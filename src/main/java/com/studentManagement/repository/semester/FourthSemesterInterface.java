package com.studentManagement.repository.semester;

import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.first;
import com.studentManagement.entity.semester.fourth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FourthSemesterInterface extends JpaRepository<fourth,Long>
{
    @Query("SELECT s FROM Student s JOIN FETCH s.fourth")
    List<com.studentManagement.entity.Student> findAllUsersWithAddressfourth();

    @Query("SELECT s FROM fourth s where s.id1=:value")
    List<fourth> findUsersWithId(@Param("value") String value);
}
