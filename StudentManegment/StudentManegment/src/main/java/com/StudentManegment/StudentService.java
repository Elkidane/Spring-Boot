package com.StudentManegment;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id){
        return studentRepository.findById(id);
    }
    public Student updateStudent(Long id, Student studentDetails) {
        return studentRepository.findById(id).map(student -> {
            student.setName(studentDetails.getName());
            student.setLastName(studentDetails.getLastName());
            student.setEmail(studentDetails.getEmail());
            student.setAge(studentDetails.getAge());
            student.setDepartment(studentDetails.getDepartment());
            return studentRepository.save(student);
        }).orElseThrow(() -> new EntityNotFoundException("Student with ID " + id + " not found"));
    }
    public void deleteStudent(Long id){
        if(!studentRepository.existsById(id)){
            throw new EntityNotFoundException("Student with id :"+ id +" not found!");
        }
        studentRepository.deleteById(id);
    }
}
