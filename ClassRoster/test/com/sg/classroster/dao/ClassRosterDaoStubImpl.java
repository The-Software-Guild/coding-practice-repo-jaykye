package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;

import java.util.ArrayList;
import java.util.List;

public class ClassRosterDaoStubImpl implements ClassRosterDao{
    public Student onlyStudent;

    public ClassRosterDaoStubImpl() {
        onlyStudent = new Student("0001");
        onlyStudent.setFirstName("Ada");
        onlyStudent.setLastName("Lovelace");
        onlyStudent.setCohort("Java-May-1845");
    }

    public ClassRosterDaoStubImpl(Student testStudent){
        this.onlyStudent = testStudent;
    }

    @Override
    public Student addStudent(String studentId, Student student)
            throws ClassRosterPersistenceException {
        // Pretend you are accessing dao.
        if (studentId.equals(onlyStudent.getStudentId())) {
            return onlyStudent;  // This is when there was already the same student number.
        } else {
            return null;  // This is when new student is added. -- But it won't be able to in this test.
        }
    }

    @Override
    public List<Student> getAllStudents()
            throws ClassRosterPersistenceException {
        List<Student> studentList = new ArrayList<>();
        studentList.add(onlyStudent);
        return studentList;
    }

    @Override
    public Student getStudent(String studentId)
            throws ClassRosterPersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())) {
            return onlyStudent;
        } else {
            return null;
        }
    }

    @Override
    public Student removeStudent(String studentId)
            throws ClassRosterPersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())) {
            return onlyStudent;
        } else {
            return null;
        }
    }
}
