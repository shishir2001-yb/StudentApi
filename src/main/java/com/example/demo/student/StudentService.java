package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


/*THIS IS THE SERVICE LAYER*/
//@Component same as @Service
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents()
    {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        /*


            THE findStudentByEmail method is from StudentRepository(interface)

         */
        Optional<Student> studentOptional=studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent())
        {
            throw new IllegalStateException("EMAIL TAKEN");
        }

         studentRepository.save(student);
        System.out.println(student);
    }


    public void deleteStudent(Long studentId) {
//        studentRepository.deleteById(studentId);
        if(!studentRepository.existsById(studentId))
        {
            throw new IllegalStateException("Student  with id "+studentId+"does not exits");
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
    Student student=studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException(
                    "student with id "+ studentId+"does not exits"
            ));
        if(name!=null&&name.length()>0&&
            !Objects.equals(student.getName(),name))
        {
           student.setName(name);
        }
        if(email!=null&& email.length()>0&&
                !Objects.equals(student.getEmail(),email))
        {
            Optional<Student> studentOptional=studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent())
                throw new IllegalStateException("Student with email "+email+"exits");
            student.setEmail(email);
        }

    }
}
