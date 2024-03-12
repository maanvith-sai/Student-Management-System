package com.studentManagement.repository.semester;

import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.fifth;
import com.studentManagement.entity.semester.first;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FifthSemesterInterface extends JpaRepository<fifth,Long>
{
    @Query("SELECT s FROM Student s JOIN FETCH s.fifth")
    List<Student> findAllUsersWithAddressfifth();

    @Query("SELECT s FROM fifth s where s.id1=:value")
    List<fifth> findUsersWithId(@Param("value") String value);
}
