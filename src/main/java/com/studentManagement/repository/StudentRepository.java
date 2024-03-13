package com.studentManagement.repository;


import com.studentManagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.studentManagement.entity.semester.*;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("SELECT u FROM Student u WHERE u.id = ?1")
    Student findByUserId(String userId);

    @Query("SELECT f FROM first f WHERE f.id1 = ?1")
    List<first> getFirstSemester(String userId);

    @Query("SELECT f FROM second f WHERE f.id1 = ?1")
    List<second> getSecondSemester(String userId);

    @Query("SELECT f FROM third f WHERE f.id1 = ?1")
    List<third> getThirdSemester(String userId);

    @Query("SELECT f FROM fourth f WHERE f.id1 = ?1")
    List<fourth> getFourthSemester(String userId);

    @Query("SELECT f FROM fifth f WHERE f.id1 = ?1")
    List<fifth> getFifthSemester(String userId);

    @Query("SELECT f FROM sixth f WHERE f.id1 = ?1")
    List<sixth> getSixthSemester(String userId);

    @Query("SELECT f FROM seventh f WHERE f.id1 = ?1")
    List<seventh> getSeventhSemester(String userId);

    @Query("SELECT f FROM eighth f WHERE f.id1 = ?1")
    List<eighth> getEighthSemester(String userId);


    @Query("SELECT s FROM Student s WHERE s.teacherId = ?1 ORDER BY s.id ASC")
    List<Student> viewStudents(String userId);

}
