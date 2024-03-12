package com.studentManagement.service;

import com.studentManagement.entity.Teacher;
import com.studentManagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeachers()
    {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher saveTeacher(Teacher teacher)
    {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacherById(String teacherId) {
        return teacherRepository.findById(teacherId).get();
    }



    @Override
    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }



    @Override
    public void deleteTeacher(String teacherId) {
        teacherRepository.deleteById(teacherId);
    }



}
