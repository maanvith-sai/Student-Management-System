package com.studentManagement.service;


import com.studentManagement.entity.Student;

import java.util.List;

public interface StudentService {
	
	List<Student> getAllStudents();
	Student saveStudent(Student student);



	Student getStudentById(String studentId);
	Student updateStudent(Student student);

//	List<Student> findAllStudentsOfTeacher();


	void deleteStudent(String studentId);
}
