package com.studentManagement.entity.semester;

import com.studentManagement.entity.Student;
import jakarta.persistence.*;

@Entity
@Table(name = "third")
public class third {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id1")
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
    @JoinColumn(name = "student_id2", nullable = false)
    private Student student2;

    public third(Long id, String id1, String subjectName, String internals, String externals, String regulation, String attendance, Student student1) {
        this.id = id;
        this.id1 = id1;
        this.subjectName = subjectName;
        this.internals = internals;
        this.externals = externals;
        this.regulation = regulation;
        this.attendance = attendance;
        this.student2 = student1;
    }
    public third()
    {
    }
    public String getId1()
    {
        return id1;
    }
    public void setId1(String id1)
    {
        this.id1 = id1;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectId) {
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


    public void setStudent2(Student std)
    {
        student2=std;
    }
    public Student getStudent2()
    {
        return student2;
    }
}
