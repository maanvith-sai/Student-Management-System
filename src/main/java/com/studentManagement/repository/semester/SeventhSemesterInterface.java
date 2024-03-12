package com.studentManagement.repository.semester;

import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.first;
import com.studentManagement.entity.semester.seventh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeventhSemesterInterface extends JpaRepository<seventh,Long> {
    @Query("SELECT s FROM Student s JOIN FETCH s.seventh")
    List<Student> findAllUsersWithAddressseventh();

    @Query("SELECT s FROM seventh s where s.id1=:value")
    List<seventh> findUsersWithId(@Param("value") String value);
}
