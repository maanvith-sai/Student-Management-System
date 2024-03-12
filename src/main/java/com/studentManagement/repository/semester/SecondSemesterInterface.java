package com.studentManagement.repository.semester;

import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.first;
import com.studentManagement.entity.semester.second;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SecondSemesterInterface extends JpaRepository<second,Long>
{
    @Query("SELECT s FROM Student s JOIN FETCH s.second")
    List<Student> findAllUsersWithAddresssecond();

    @Query("SELECT s FROM second s where s.id1=:value")
    List<second> findUsersWithId(@Param("value") String value);
}
