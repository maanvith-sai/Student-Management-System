package com.studentManagement.repository.semester;

import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.first;
import com.studentManagement.entity.semester.third;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThirdSemesterInterface extends JpaRepository<third,Long> {
    @Query("SELECT s FROM Student s JOIN FETCH s.third")
    List<Student> findAllUsersWithAddressthird();

    @Query("SELECT s FROM third s where s.id1=:value")
    List<third> findUsersWithId(@Param("value") String value);
}
