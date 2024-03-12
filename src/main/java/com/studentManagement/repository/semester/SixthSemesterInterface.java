package com.studentManagement.repository.semester;

import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.first;
import com.studentManagement.entity.semester.sixth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SixthSemesterInterface extends JpaRepository<sixth,Long> {
    @Query("SELECT s FROM Student s JOIN FETCH s.sixth")
    List<Student> findAllUsersWithAddresssixth();

    @Query("SELECT s FROM sixth s where s.id1=:value")
    List<sixth> findUsersWithId(@Param("value") String value);
}
