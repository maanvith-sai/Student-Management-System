package com.studentManagement.entity.semester;

import com.studentManagement.entity.Student;
import jakarta.persistence.*;

@Entity
@Table(name ="first")
public class first
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="id1")
    private String id1;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "internals")
    private String internals;

    @Column(name = "externals")
    private String externals;

    @Column(name = "regulation")
    private String regulation;

    @Column(name = "attendance")
    private String attendance;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public first(Long id,String id1,String subjectName, String internals, String externals, String regulation, String attendance,Student student)
    {
        this.id=id;
        this.id1=id1;
        this.subjectName = subjectName;
        this.internals = internals;
        this.externals = externals;
        this.regulation = regulation;
        this.attendance = attendance;
        this.student=student;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public first() {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectId)
    {
        this.subjectName = subjectId;
    }

    public String getInternals() {
        return internals;
    }

    public void setInternals(String internals) {
        this.internals = internals;
    }

    public String getExternals() {
        return externals;
    }

    public void setExternals(String externals) {
        this.externals = externals;
    }

    public String getRegulation() {
        return regulation;
    }

    public void setRegulation(String regulation) {
        this.regulation = regulation;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }
    public Student getStudent()
    {
        return student;
    }
}