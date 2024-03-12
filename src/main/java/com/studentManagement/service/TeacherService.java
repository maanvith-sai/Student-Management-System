package com.studentManagement.service;

import com.studentManagement.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    Teacher saveTeacher(Teacher teacher);
    Teacher getTeacherById(String teacherId);
    Teacher updateTeacher(Teacher teacher);
    void deleteTeacher(String teacherId);
}
